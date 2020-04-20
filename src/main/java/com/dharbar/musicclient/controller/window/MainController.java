package com.dharbar.musicclient.controller.window;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Component;

@Component
public class MainController {

	private Stage dirAlertStage;
	private Parent dirAlert;
	@FXML
	private VBox root;
	@FXML
	private Menu playListMenu;
	@FXML
	private TextField textField;
	@FXML
	private Button addBtn;
	@FXML
	private Button playBtn;
	@FXML
	private TabPane tabPane;
	@FXML
	private CheckBox bigAttitude, AThink, ASad, ALoud, AHate, canSleep, swing, move, classic;
	@FXML
	private TextField authorSearchField;

	private FileChooser fileChooser;
//	private MscNameDTO currentMsc;
	private ClipboardContent clipboardContent;
//	private MainApp mainApp;
//	private PurgatoryTabManager purgatoryTabManager;
//	private FixTabManager fixTabManager;
//	private MscPLayListTabManager mscPLayListTabManager;
//	private IPlayListTabManager currentTabManager;

	@FXML
	public void initialize() {
		clipboardContent = new ClipboardContent();

//		initFileChooser();
//		initTabManagers();
//		tabPane.getTabs().add(purgatoryTabManager.getTab());
//		tabPane.getTabs().add(fixTabManager.getTab());
	}

	private void initTabManagers() {
//		UserPreferences userPreferences = UserPreferences.getInstance();
//		mscPLayListTabManager = new MscPLayListTabManager(this, tabPane, userPreferences);
//		purgatoryTabManager = new PurgatoryTabManager(this, mscPLayListTabManager, userPreferences);
//		fixTabManager = new FixTabManager(purgatoryTabManager, userPreferences);
	}

//	private void initFileChooser() {
//		fileChooser = new FileChooser();
//		fileChooser.getExtensionFilters().add(new ExtensionFilter("playList", "*.m3u"));
//	}

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
		if (dirAlert == null) {
			initDirConfig();
		}
		if (dirAlertStage == null) {
			dirAlertStage = new Stage();
			dirAlertStage.initModality(Modality.WINDOW_MODAL);
			dirAlertStage.initStyle(StageStyle.DECORATED);
			dirAlertStage.setResizable(false);
			dirAlertStage.setScene(new Scene(dirAlert, 570, 170));
			dirAlertStage.initOwner(root.getScene().getWindow());
		}
		dirAlertStage.showAndWait();
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
	public void createAuthorTab() {
//		String name = authorSearchField.getText();
//		if (name != null && !Objects.equals(name, "")) {
//			if (name.matches(UserPropertyUtil.AUTHOR_REGEX)) {
//				Tab tab = mscPLayListTabManager.getTab(name);
//				if (tab != null) {
//					tabPane.getTabs().add(tab);
//					Notificator.tabCreate(name);
//				}
//			} else {
//				Notificator.nameWarning(name);
//				authorSearchField.requestFocus();
//				authorSearchField.setText("LOL");
//			}
//		}
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
		clipboardContent.putString(textField.getText());
		Clipboard.getSystemClipboard().setContent(clipboardContent);
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
