package com.dharbar.musicclient.controller.tab.cell;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Component;

public class CellController {
	@FXML
	protected HBox hBox;
	@FXML
	protected TextField textField;
	@FXML
	protected Button removeBtn;
	@FXML
	protected Button renameBtn;
}
