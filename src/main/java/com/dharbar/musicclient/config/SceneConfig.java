package com.dharbar.musicclient.config;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class SceneConfig {
	@Value("classpath:/Views/Windows/mainApp.fxml") Resource mainStageResource;

//	@Bean
//	public FXMLLoader fxmlLoader(ApplicationContext applicationContext) {
//		final FXMLLoader fxmlLoader = new FXMLLoader();
//		fxmlLoader.setControllerFactory(applicationContext::getBean);
//		return fxmlLoader;
//	}
//
//	@Bean
//	public Scene mainScene(FXMLLoader fxmlLoader) throws IOException {
//		Parent parent = fxmlLoader.load(mainStageResource.getURL());
//		return new Scene(parent, 800, 600);
//	}
}
