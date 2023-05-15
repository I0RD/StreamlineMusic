package com.app.StreamlineMusic;

import com.app.StreamlineMusic.GUI.AppStage;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamlineMusicApplication {

	public static void main(String[] args) {

		Application.launch(AppStage.class,args);
	}


}
