package com.app.StreamlineMusic.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneManager {

    static Scene scene;
    static MainScene mainScene;
    static LoginScene loginScene;
    static RegisterScene registerScene;

    public SceneManager() throws IOException {
        loginScene=new LoginScene(new FXMLLoader(getClass().getResource("/LoginFXML.fxml")));
        registerScene=new RegisterScene(new FXMLLoader(getClass().getResource("/RegisterFXML.fxml")));
        mainScene=new MainScene(new FXMLLoader(getClass().getResource("/MainFXML.fxml")));
        loginScene.fxmlController.signUp.setOnAction(actionEvent -> setScene("REGISTER"));
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