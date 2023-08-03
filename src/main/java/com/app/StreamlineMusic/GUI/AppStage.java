package com.app.StreamlineMusic.GUI;

import com.app.StreamlineMusic.StreamlineMusicApplication;
import com.app.StreamlineMusic.utils.FileResourcesUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;


public class AppStage extends Application {
    private ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {

        launch(args);
    }
    @Override
    public void init(){
        applicationContext= new SpringApplicationBuilder(StreamlineMusicApplication.class).run();
    }
    @Override
    public void start(Stage stage) throws Exception {
        applicationContext.publishEvent(new StageReadyEvent(stage));
        SceneManager.scene = new Scene(SceneManager.loginScene.getLoginParent());
        stage.getIcons().add(new Image(new FileResourcesUtils().getFileFromResource("images/Przechwytywanie.png")));
        stage.setHeight(800);
        stage.setWidth(1280);
        stage.setTitle("Streamline Music");
        stage.setScene(SceneManager.setScene("LOGIN"));
        stage.show();
    }
    @Override
    public void stop(){
        applicationContext.close();
        Platform.exit();
    }
    static class StageReadyEvent extends ApplicationEvent{
        public StageReadyEvent(Stage stage){
            super(stage);
        }
        public Stage getStage(){
            return ((Stage)getSource());
        }
    }
}

