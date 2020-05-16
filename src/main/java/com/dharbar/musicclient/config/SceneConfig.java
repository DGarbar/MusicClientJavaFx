package com.dharbar.musicclient.config;

import com.dharbar.musicclient.controller.window.MainController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SceneConfig {

	@Bean
	public Scene mainScene(FxWeaver fxWeaver) {
		Parent parent = fxWeaver.loadView(MainController.class);
		return new Scene(parent, 640, 390);
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
