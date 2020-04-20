package com.dharbar.musicclient;

import com.dharbar.musicclient.Starter.StageReadyEvent;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
	@Value("classpath:/Views/Windows/mainApp.fxml") Resource mainStageResource;

//	private final Scene mainScene;
	ApplicationContext applicationContext;
//	public StageInitializer(Scene mainScene) {
//		this.mainScene = mainScene;
//	}


	public StageInitializer(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@SneakyThrows
	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		final FXMLLoader fxmlLoader = new FXMLLoader(mainStageResource.getURL());
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		Parent parent = fxmlLoader.load();
		Stage stage = event.getStage();
		stage.setScene(new Scene(parent, 800, 600));
		stage.show();
	}
}
