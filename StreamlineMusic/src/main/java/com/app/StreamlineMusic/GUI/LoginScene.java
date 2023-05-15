package com.app.StreamlineMusic.GUI;

import com.app.StreamlineMusic.controller.AuthController;
import com.app.StreamlineMusic.controller.FxmlController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class LoginScene  {

    private Parent loginScene;
    public FxmlController fxmlController;

    public LoginScene(FXMLLoader loader) throws IOException {
        loginScene=loader.load();
        fxmlController=loader.getController();
        fxmlController.signIn.setOnAction(actionEvent ->
                        login()
                );
    }

    public Parent getLoginParent(){
        return loginScene;
    }

    private void login(){
        if(AuthController.login(fxmlController.userName.getText(),fxmlController.password.getText())){
            SceneManager.setScene("MAIN");
        }
    }
}