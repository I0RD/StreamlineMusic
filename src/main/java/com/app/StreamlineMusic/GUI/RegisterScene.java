package com.app.StreamlineMusic.GUI;

import com.app.StreamlineMusic.controller.AuthController;
import com.app.StreamlineMusic.controller.FxmlController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.*;
import javafx.stage.StageStyle;

import java.io.IOException;

public class RegisterScene {

    private final Parent registerScene;
    public FxmlController fxmlController;
    public RegisterScene(FXMLLoader loader) throws IOException {
        registerScene=loader.load();
        fxmlController=loader.getController();
        fxmlController.signUp.setOnAction(actionEvent ->
                register()
                );
    }
    public Parent getRegisterParent() {
        return registerScene;
    }

    private void register(){
        String result=AuthController.register(fxmlController.name.getText(),fxmlController.userName.getText(),fxmlController.email.getText(),fxmlController.password.getText());
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(result);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
        if(!alert.isShowing()){
            SceneManager.setScene("LOGIN");
        }
    }
}
