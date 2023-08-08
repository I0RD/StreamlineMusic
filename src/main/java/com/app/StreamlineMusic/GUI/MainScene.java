package com.app.StreamlineMusic.GUI;

import com.app.StreamlineMusic.controller.FxmlController;
import com.app.StreamlineMusic.controller.SongController;
import com.app.StreamlineMusic.entity.Song;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.List;

public class MainScene {

    private final Parent mainScene;
    public FxmlController fxmlController;

    public MainScene(FXMLLoader fxmlLoader) throws IOException {
        mainScene=fxmlLoader.load();
        fxmlController=fxmlLoader.getController();
        List<Song> songs;
        songs=SongController.getAllSongs();
        for(int i=0;i<songs.size();i++){
            GridPane ass = new GridPane();
            ass.setHgap(10);
            ass.setVgap(10);
            ass.add(new Label(songs.get(i).getName()),0,0);
            ass.add(new Label(songs.get(i).getGenre()),0,1);
            ass.add(new Label(songs.get(i).getAuthor()),1,0);
            HBox hBox=new HBox(ass);
            fxmlController.grid.add(hBox,0,i+1);
        }
        fxmlController.grid.setOnMouseClicked(mouseEvent -> fxmlController.control_music.setVisible(true));
    }

    public Parent getMainParent(){
        return mainScene;
    }
}
