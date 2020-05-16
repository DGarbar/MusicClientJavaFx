package com.dharbar.musicclient.config;

import javafx.scene.input.ClipboardContent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

	@Bean
	public ClipboardContent clipboardContent() {
		return new ClipboardContent();
	}

}
