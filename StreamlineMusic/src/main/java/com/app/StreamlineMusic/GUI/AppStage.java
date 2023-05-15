package com.app.StreamlineMusic.GUI;

import com.app.StreamlineMusic.StreamlineMusicApplication;
import com.app.StreamlineMusic.utils.FileResourcesUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;


public class AppStage extends Application {
    private ConfigurableApplicationContext applicationContext;
    private Scene scene;
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
        SceneManager sceneManager=new SceneManager();
        scene=new Scene(sceneManager.loginScene.getLoginParent());
        sceneManager.scene=scene;
        stage.getIcons().add(new Image(new FileResourcesUtils().getFileFromResource("images/Przechwytywanie.png")));
        stage.setHeight(400);
        stage.setWidth(700);
        stage.setTitle("Streamline Music");
        stage.setScene(sceneManager.setScene("MAIN"));
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

