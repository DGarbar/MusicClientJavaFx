package com.dharbar.musicclient.config;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class SceneConfig {

	@Value("classpath:/Views/Windows/mainApp.fxml")
	Resource mainStageResource;
	@Value("classpath:/Views/Tabs/SimpleTab.fxml")
	Resource simpleTabResource;
	@Value("classpath:/Views/Cells/SimpleCell.fxml")
	Resource simpleCellResource;

	@Bean
	public FXMLLoader fxmlLoader(ApplicationContext applicationContext) {
		final FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		return fxmlLoader;
	}

	@Bean
	public Scene mainScene(FXMLLoader fxmlLoader) throws IOException {
		Parent parent = fxmlLoader.load(mainStageResource.getURL());
		return new Scene(parent, 640, 375);
	}
//
//	@Bean
//	@Scope("prototype")
//	public Tab paneTab(FXMLLoader fxmlLoader) throws IOException {
//		final Pane pane = fxmlLoader.load(simpleTabResource.getURL());
//		Tab tab = new Tab("name", pane);
//		tab.setClosable(true);
//		return tab;
//	}
}
