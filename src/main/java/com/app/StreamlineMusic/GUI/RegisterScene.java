package com.app.StreamlineMusic.GUI;

import com.app.StreamlineMusic.controller.AuthController;
import com.app.StreamlineMusic.controller.FxmlController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if(fxmlController.name.getText().isEmpty()||fxmlController.userName.getText().isEmpty()||fxmlController.email.getText().isEmpty()||fxmlController.password.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error register");
            alert.setHeaderText("data not correctly completed");
            alert.setContentText(null);

            alert.showAndWait();
        }else {
            String regex = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher=pattern.matcher(fxmlController.email.getText());
            if(matcher.matches()) {
                String result = AuthController.register(fxmlController.name.getText(), fxmlController.userName.getText(), fxmlController.email.getText(), fxmlController.password.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setContentText(result);
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
                if (!alert.isShowing()) {
                    SceneManager.setScene("LOGIN");
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error register");
                alert.setHeaderText("wrong e-mail");
                alert.setContentText(null);

                alert.showAndWait();
            }
        }

    }
}
