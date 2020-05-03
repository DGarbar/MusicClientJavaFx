package com.dharbar.musicclient.controller.tab;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

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
