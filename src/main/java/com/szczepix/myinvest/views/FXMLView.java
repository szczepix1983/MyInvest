package com.szczepix.myinvest.views;


import com.szczepix.myinvest.managers.StageManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public abstract class FXMLView implements Initializable {

    @Lazy
    @Autowired
    protected StageManager stageManager;

    protected void enableButton(final Button button, final EventHandler<ActionEvent> callback) {
        new InteractiveButton(button, callback);
    }

    private class InteractiveButton {

        final Button button;

        InteractiveButton(final Button button, EventHandler<ActionEvent> callback) {
            this.button = button;
            this.button.setOnAction(callback);
            this.button.setOnMouseEntered(this::handleOnOver);
            this.button.setOnMouseExited(this::handleOnOut);
        }

        private void handleOnOver(MouseEvent event) {
            this.button.setBlendMode(BlendMode.SCREEN);
        }

        private void handleOnOut(MouseEvent event) {
            this.button.setBlendMode(BlendMode.SRC_ATOP);
        }
    }
}
