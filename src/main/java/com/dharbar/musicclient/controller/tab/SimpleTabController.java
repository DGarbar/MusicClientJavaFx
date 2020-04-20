package com.dharbar.musicclient.controller.tab;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

public class SimpleTabController {

	@FXML
	// TODO change
	private ListView<String> listView;

	@FXML
	public void initialize() {
//		listViewItems = listView.getItems();
//		initListView();
	}

	private void initListView() {
		listView.setItems(listView.getItems().sorted());
//		listView.setCellFactory(param -> {
//			MscCellController mscCellController = new MscCellController(this);
//			mscCellController.setTextMatcher(UserPropertyUtil.SONG_REGEX);
//			return new CustomCell<>(mscCellController);
//		});
	}
//
//	private Tab createTab(String name, List<String> melodies) {
//		Tab tab = null;
//		if (CollectionUtils.isNotEmpty(melodies)) {
//
//			tab.setClosable(true);
//
//			dtoSet.stream().filter(tabController::isSatisfy)
//				.forEach(tabController::addCellItem);
//			tabControllerMap.put(name, tabController);
//		} else {
////			Notificator.emptyListWarning(name);
//		}
//		return tab;
//	}


}
