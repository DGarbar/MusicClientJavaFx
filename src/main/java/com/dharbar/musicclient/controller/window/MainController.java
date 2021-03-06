package com.dharbar.musicclient.controller.window;

import com.dharbar.musicclient.controller.window.dto.Genre;
import com.dharbar.musicclient.controller.window.dto.Mood;
import com.dharbar.musicclient.service.mediaplayer.MediaPlayerService;
import com.dharbar.musicclient.service.requester.Requester;
import com.dharbar.musicclient.service.requester.dto.Music;
import com.dharbar.musicclient.service.requester.dto.MusicAttributes;
import com.dharbar.musicclient.service.subscriber.MusicSubscriber;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxmlView;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@FxmlView("/Views/Windows/mainApp.fxml")
public class MainController {

	private final ClipboardContent clipboardContent;
	private final Requester requester;
	private MusicSubscriber currentListSubscription;
	private MediaPlayerService mediaPlayerService;

	@FXML
	private VBox root;
	@FXML
	private Menu playListMenu;
	@FXML
	private TextField musicField;
	@FXML
	private Button nextBtn;
	@FXML
	private Button playBtn;
	@FXML
	private ListView<Music> listView;
	@FXML
	private CheckComboBox<String> moodCheckComboBox;
	@FXML
	private CheckComboBox<String> genreCheckComboBox;
	@FXML
	private ComboBox<String> authorSearch;
	@FXML
	private TextArea authorSearchTextArea;

	public MainController(
		ClipboardContent clipboardContent, Requester requester) {
		this.clipboardContent = clipboardContent;
		this.requester = requester;

//		Media media = new Media("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3");
//		MediaPlayer mediaPlayer = new MediaPlayer(media);
//		mediaPlayer.play();
//		mediaPlayer.setVolume(20);
	}

	@FXML
	public void initialize() {
		initMediaPlayerService();
		initAuthorSearch();
		initMusicList();
		initGenreComboBox();
		initMoodComboBox();
	}

	private void initAuthorSearch() {
		authorSearch.setCellFactory(stringListView -> {
			ListCell<String> cell = new ListCell<>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					setText(empty ? null : item);
				}
			};
			cell.setOnMousePressed(e -> authorMouseChoose(cell));
			return cell;
		});
	}

	private void authorMouseChoose(ListCell<String> cell) {
		if (!cell.isEmpty()) {
			ObservableList<Music> listViewItems = listView.getItems();
			listViewItems.clear();
			String text = cell.getText();
			Flux<Music> musicFlux = requester.searchMusicByArtist(text);

			subscribeAndFullList(musicFlux, listViewItems::add);
		}
	}

	private void initMusicList() {
		mediaPlayerService.setPlayListAndPlay(listView.getItems());

		listView.setCellFactory(stringListView -> new ListCell<>() {
			@Override
			protected void updateItem(Music item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty
					? null
					: String.format("%s - %s", item.getArtist(), item.getSongName()));
			}
		});
	}

	private void initMoodComboBox() {
		List<String> moods = Arrays.stream(Mood.values()).map(Mood::getNormal)
			.collect(Collectors.toList());
		moodCheckComboBox.getItems().addAll(moods);
	}

	private void initGenreComboBox() {
		List<String> genres = Arrays.stream(Genre.values()).map(Genre::getNormal)
			.collect(Collectors.toList());
		genreCheckComboBox.getItems().addAll(genres);
	}

	private void initMediaPlayerService() {
		mediaPlayerService = new MediaPlayerService(this::chooseMusic, this::loadMore);
	}

	@FXML
	public void listClick() {
		Music selectedItem = listView.getSelectionModel().getSelectedItem();

		if (selectedItem != null) {
			mediaPlayerService.play(selectedItem);
		}
	}

	private void chooseMusic(Music music) {
		musicField.textProperty()
			.setValue(String.format("%s - %s", music.getArtist(), music.getSongName()));
		musicField.end();

		listView.getSelectionModel().select(music);

		IndexedCheckModel<String> moodCheckModel = moodCheckComboBox.getCheckModel();
		moodCheckModel.clearChecks();
		List<String> tags = music.getTags();
		if (tags != null) {
			tags.forEach(moodCheckModel::check);
		}

		IndexedCheckModel<String> genreCheckModel = genreCheckComboBox.getCheckModel();
		genreCheckModel.clearChecks();
		List<String> genres = music.getGenres();
		if (genres != null) {
			genres.forEach(genreCheckModel::check);
		}
	}

	@FXML
	public void play() {
		if (mediaPlayerService.isPlaying()) {
			mediaPlayerService.pause();
		} else {
			mediaPlayerService.play();
		}
	}

	@FXML
	public void searchAuthor() {
		String search = authorSearch.getEditor().getText();
		if (StringUtils.isNoneBlank(search)) {
			ObservableList<String> items = authorSearch.getItems();
			items.clear();
			requester.searchArtists(search)
				.doOnNext(items::addAll)
				.subscribe();
		}
	}

	@FXML
	public void updateAttributes() {
		Music currentMusic = mediaPlayerService.getCurrentMusic();
		if (currentMusic != null) {
			List<String> genres = genreCheckComboBox.getCheckModel().getCheckedItems();

			List<String> tags = moodCheckComboBox.getCheckModel().getCheckedItems();

			currentMusic.setTags(tags);
			currentMusic.setGenres(genres);

			requester.updateMusic(currentMusic)
				.doOnError(throwable -> log.error(throwable.getMessage(), throwable))
				.subscribe();
		}
	}

	@FXML
	public void searchAttributes() {
		String text = authorSearchTextArea.getText();
		List<String> artists = StringUtils.isBlank(text)
			? Collections.emptyList()
			: Arrays.asList(StringUtils.stripToEmpty(text).split(", "));
		List<String> tags = moodCheckComboBox.getCheckModel().getCheckedItems();
		List<String> genres = genreCheckComboBox.getCheckModel().getCheckedItems();

		if (!(CollectionUtils.isEmpty(artists)
			&& CollectionUtils.isEmpty(tags)
			&& CollectionUtils.isEmpty(genres))) {
			ObservableList<Music> listViewItems = listView.getItems();
			listViewItems.clear();
			Flux<Music> musicFlux = requester
				.searchByAttributes(MusicAttributes.of(artists, genres, tags));

			subscribeAndFullList(musicFlux, listViewItems::add);
		}
	}

	private void subscribeAndFullList(Flux<Music> musicFlux, Consumer<Music> onNext) {
		if (currentListSubscription != null) {
			currentListSubscription.cancel();
		}
		currentListSubscription = new MusicSubscriber(onNext);
		musicFlux.subscribe(currentListSubscription);
	}

	@FXML
	public void loadMore() {
		if (currentListSubscription != null) {
			currentListSubscription.req();
		}
	}

	// TODO
//	@FXML
//	public void createPlayListTab() {
//		String name = authorSearchField.getText();
//		if (name != null && !Objects.equals(name, "")) {
//			MscNameDTO template = writeMscNameTags(new MscNameDTO(null, null));
//			Tab tab = mscPLayListTabManager.getTab(name, template);
//			if (tab != null) {
//				tabPane.getTabs().add(tab);
//				Notificator.tabCreate(name);
//			}
//		} else {
//			Notificator.nameWarning(name);
//		}
//	}

	@FXML
	private void nextMsc() {
		mediaPlayerService.next();
	}

	@FXML
	public void copyTextField() {
		clipboardContent.putString(musicField.getText());
		Clipboard.getSystemClipboard().setContent(clipboardContent);
	}

	@FXML
	private void showInfo() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/Views/Windows/About.fxml"));
			Stage info = new Stage();
			info.initModality(Modality.WINDOW_MODAL);
			info.initStyle(StageStyle.DECORATED);
			info.setResizable(false);
			info.setScene(new Scene(fxmlLoader.load(), 600, 200));
			info.initOwner(root.getScene().getWindow());
			info.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
