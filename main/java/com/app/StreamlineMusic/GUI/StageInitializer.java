package com.app.StreamlineMusic.GUI;

import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;

public class StageInitializer implements ApplicationListener<AppStage.StageReadyEvent> {
    @Override
    public void onApplicationEvent(AppStage.StageReadyEvent event) {
        Stage stage=event.getStage();
    }
}
