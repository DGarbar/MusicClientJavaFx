package com.dharbar.musicclient.controller.window;

import com.dharbar.musicclient.service.requester.Requester;
import com.dharbar.musicclient.service.requester.dto.Music;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxmlView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/Views/Windows/mainApp.fxml")
public class MainController {

	private final ClipboardContent clipboardContent;
	private final Requester requester;
	@FXML
	private VBox root;
	@FXML
	private Menu playListMenu;
	@FXML
	private TextField musicField;
	@FXML
	private Button addBtn;
	@FXML
	private Button playBtn;
	@FXML
	private ListView<Music> listView;
	@FXML
	private CheckBox bigAttitude, AThink, ASad, ALoud, AHate, canSleep, swing, move, classic;
	@FXML
	private ComboBox<String> authorSearch;
	private Music currentMusic;
	private MediaPlayer currentMediaPlayer;


	public MainController(ClipboardContent clipboardContent, Requester requester) {
		this.clipboardContent = clipboardContent;
		this.requester = requester;
	}

	@FXML
	public void initialize() {
		initAuthorSearch();
		initMusicList();
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
			requester.searchMusic(text)
				.subscribe(listViewItems::add);
		}
	}

	private void initMusicList() {
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

	@FXML
	public void listClick() {
		Music selectedItem = listView.getSelectionModel().getSelectedItem();
		selectMusic(selectedItem);
	}

	private void selectMusic(Music music) {
		if (!music.equals(currentMusic)) {
			if (currentMediaPlayer != null) {
				currentMediaPlayer.stop();
			}
			Media media = new Media(music.getFileUrl());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();
			mediaPlayer.setVolume(20);
			currentMediaPlayer = mediaPlayer;
			currentMusic = music;
		}

	}

//	public void selectNone() {
//		currentTabManager = null;
//		currentMsc = null;
//		addBtn.setDisable(true);
//		playBtn.setDisable(true);
//		authorSearchField.setText(null);
//		textField.clear();
//		setEmptyTags();
//	}

//	public void selectMsc(MscNameDTO mscNameDTO, IPlayListTabManager playListTabManager) {
//		addBtn.setDisable(false);
//		playBtn.setDisable(!mscNameDTO.getExist());
//		textField.setText(mscNameDTO.toString());
//		textField.end();
//		currentTabManager = playListTabManager;
//		currentMsc = mscNameDTO;
//		writeTagsFromMscName(mscNameDTO);
//	}

	@FXML
	public void showDirAlert() {
//		if (dirAlert == null) {
//			initDirConfig();
//		}
//		if (dirAlertStage == null) {
//			dirAlertStage = new Stage();
//			dirAlertStage.initModality(Modality.WINDOW_MODAL);
//			dirAlertStage.initStyle(StageStyle.DECORATED);
//			dirAlertStage.setResizable(false);
//			dirAlertStage.setScene(new Scene(dirAlert, 570, 170));
//			dirAlertStage.initOwner(root.getScene().getWindow());
//		}
//		dirAlertStage.showAndWait();
	}

	@FXML
	public void showAudioChooser() {
//		mainApp.showAudioChooser();
	}

	@FXML
	public void play() {
//		if (currentMsc != null) {
//			try {
//				mainApp.getAudioPlayer().play(currentMsc);
//			} catch (IOException e) {
//				showAudioChooser();
//			}
//		}
	}

//	public void setMainApp(MainApp mainApp) {
//		this.mainApp = mainApp;
//	}

	@FXML
	public void searchAuthor() {
		String search = authorSearch.getEditor().getText();
		if (StringUtils.isNoneBlank(search)) {
			ObservableList<String> items = authorSearch.getItems();
			items.clear();
			requester.searchArtists(search)
				.subscribe(items::addAll);
		}
	}

	@FXML
	public void createPlayListTab() {
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
	}

	@FXML
	private void updateMscName() {
//		if (currentMsc != null) {
//			currentTabManager.update(writeMscNameTags(currentMsc));
//		}
	}

	@FXML
	private void removeMsc() {
//		if (currentMsc != null) {
//			currentTabManager.remove(currentMsc);
//		}
	}

	@FXML
	public void createPlayList() {
//		if (fileChooser == null) {
//			initFileChooser();
//		}
//		Tab tab = tabPane.getSelectionModel().getSelectedItem();
//		if (tab != null) {
//			if (!tab.getText().equals("Fix")) {
//				File file = fileChooser.showSaveDialog(root.getScene().getWindow());
//				if (file != null) {
//					boolean inPurgatory = tab.getText().equals("Purgatory");
//					Path path = file.toPath();
//					List<String> list = mscPLayListTabManager.getMscListByTabName(tab.getText());
//					M3UService.createPlayList(path, list, inPurgatory);
//				}
//			}
//		}
	}

	@FXML
	public void addToPlayList() {
//		if (currentMsc != null && !currentMsc.getInPurgatory()) {
//			if (fileChooser == null) {
//				initFileChooser();
//			}
//			File file = fileChooser.showOpenDialog(root.getScene().getWindow());
//			if (file != null) {
//				Path path = file.toPath();
//				M3UService.addToPlayList(path, currentMsc.toString());
//				addPlayListMenu(path.getName(path.getNameCount() - 1).toString(), path);
//			}
//		}
	}
//
//	private void addPlayListMenu(String name, Path listPath) {
//		if (playListMenu.getItems().stream().noneMatch(menuItem -> menuItem.getText().equals(name))) {
//			MenuItem menuItem = new MenuItem(name);
//			menuItem.setOnAction(event -> {
//				if (currentMsc != null && !currentMsc.getInPurgatory()) {
//					M3UService.addToPlayList(listPath, currentMsc.toString());
//				}
//			});
//			playListMenu.getItems().add(menuItem);
//		}
//	}

	@FXML
	public void copyTextField() {
//		clipboardContent.putString(textField.getText());
//		Clipboard.getSystemClipboard().setContent(clipboardContent);
	}


	private void initDirConfig() {
//		try {
//			FXMLLoader fxmlLoader = new FXMLLoader();
//			fxmlLoader.setLocation(getClass().getResource("/Views/Windows/DirAlert.fxml"));
//			dirAlert = fxmlLoader.load();
//			((DirConfigController) fxmlLoader.getController()).setChangePurgatoryListener(() -> {
//				fixTabManager.updateFix();
//				purgatoryTabManager.updatePurgatory();
//			});
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
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

//	private MscNameDTO writeMscNameTags(MscNameDTO mscNameDTO) {
//		mscNameDTO.setBigAttitude(bigAttitude.isSelected());
//		mscNameDTO.setAThink(AThink.isSelected());
//		mscNameDTO.setASad(ASad.isSelected());
//		mscNameDTO.setALoud(ALoud.isSelected());
//		mscNameDTO.setAHate(AHate.isSelected());
//		mscNameDTO.setCanSleep(canSleep.isSelected());
//		mscNameDTO.setSwing(swing.isSelected());
//		mscNameDTO.setMove(move.isSelected());
//		mscNameDTO.setClassic(classic.isSelected());
//		return mscNameDTO;
//	}
//
//	private void writeTagsFromMscName(MscNameDTO mscNameDTO) {
//		bigAttitude.setSelected(mscNameDTO.getBigAttitude());
//		AThink.setSelected(mscNameDTO.getAThink());
//		ASad.setSelected(mscNameDTO.getASad());
//		ALoud.setSelected(mscNameDTO.getALoud());
//		AHate.setSelected(mscNameDTO.getAHate());
//		canSleep.setSelected(mscNameDTO.getCanSleep());
//		swing.setSelected(mscNameDTO.getSwing());
//		move.setSelected(mscNameDTO.getMove());
//		classic.setSelected(mscNameDTO.getClassic());
//	}
//
//	private void setEmptyTags() {
//		bigAttitude.setSelected(false);
//		AThink.setSelected(false);
//		ASad.setSelected(false);
//		ALoud.setSelected(false);
//		AHate.setSelected(false);
//		canSleep.setSelected(false);
//		swing.setSelected(false);
//		move.setSelected(false);
//		classic.setSelected(false);
//	}

}
