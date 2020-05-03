package com.dharbar.musicclient.config;

import com.dharbar.musicclient.controller.window.MainController;
import com.dharbar.musicclient.service.requester.Requester;
import javafx.scene.input.ClipboardContent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

	@Bean
	public ClipboardContent clipboardContent() {
		return new ClipboardContent();
	}

	@Bean
	public MainController mainController(Requester requester){
		return new MainController(requester);
	}
}
