package com.app.StreamlineMusic.GUI;

import com.app.StreamlineMusic.controller.AuthController;
import com.app.StreamlineMusic.controller.FxmlController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.io.IOException;

public class LoginScene  {

    private Parent loginScene;
    public FxmlController fxmlController;

    public LoginScene(FXMLLoader loader) throws IOException {
        loginScene=loader.load();
        fxmlController=loader.getController();
        fxmlController.signUp.setOnAction(actionEvent ->
                        register()
        );
        fxmlController.signIn.setOnAction(actionEvent ->
                        login()
                );
    }

    public Parent getLoginParent(){
        return loginScene;
    }

    private void login(){
        if(fxmlController.userName.getText().isEmpty()&&fxmlController.password.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error login");
            alert.setHeaderText("Login or password is incorrect");
            alert.setContentText(null);

            alert.showAndWait();
        }
        if(AuthController.login(fxmlController.userName.getText(),fxmlController.password.getText())){
            SceneManager.setScene("MAIN");
        }
    }
    private void register(){
        SceneManager.setScene("REGISTER");
    }
}