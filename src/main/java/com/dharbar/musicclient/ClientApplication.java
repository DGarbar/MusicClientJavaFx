package com.dharbar.musicclient;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("checkstyle:hideutilityclassconstructor")
@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		Application.launch(Starter.class, args);
	}
}
