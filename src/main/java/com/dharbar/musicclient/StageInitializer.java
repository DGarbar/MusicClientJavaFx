package com.dharbar.musicclient;

import com.dharbar.musicclient.Starter.StageReadyEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

	private final Scene mainScene;

	public StageInitializer(Scene mainScene) {
		this.mainScene = mainScene;
	}

	@SneakyThrows
	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		Stage primaryStage = event.getStage();
		primaryStage.setTitle("Music Library");
		primaryStage.setResizable(false);
		primaryStage.setScene(mainScene);

		primaryStage.show();
	}
}
