package com.app.StreamlineMusic.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.awt.*;
import java.io.IOException;

public class SceneManager {

    static Scene scene;
    static MainScene mainScene;
    static LoginScene loginScene;
    static RegisterScene registerScene;
    static {
        try {
            mainScene = new MainScene(new FXMLLoader(SceneManager.class.getResource("/MainFXML2.fxml")));
            loginScene=new LoginScene(new FXMLLoader(SceneManager.class.getResource("/LoginFXML2.fxml")));
            registerScene=new RegisterScene(new FXMLLoader(SceneManager.class.getResource("/RegisterFXML2.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Scene setScene(String name){
        if ("LOGIN".equals(name)) {
            scene.setRoot(loginScene.getLoginParent());
        }else if("REGISTER".equals(name)) {
            scene.setRoot(registerScene.getRegisterParent());
        }
        else if("MAIN".equals(name)){
            scene.setRoot(mainScene.getMainParent());
        }
        return scene;
    }
}