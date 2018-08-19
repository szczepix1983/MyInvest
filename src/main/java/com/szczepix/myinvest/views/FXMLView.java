package com.szczepix.myinvest.views;


import com.szczepix.myinvest.managers.IStageManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Objects;

public abstract class FXMLView implements Initializable {

    @Lazy
    @Autowired
    public IStageManager stageManager;

    protected void enableButton(final Button button, final EventHandler<ActionEvent> callback) {
        // todo - needs refactor to cover with unit tests
        new InteractiveComponent(button);
        if(Objects.nonNull(button)) {
            button.setOnAction(callback);
        }
    }

    protected void enableCompononet(final Control component) {
        new InteractiveComponent(component);
    }

    private class InteractiveComponent {

        final Control component;

        InteractiveComponent(final Control component) {
            this.component = component;
            if(Objects.nonNull(this.component)){
                this.component.setOnMouseEntered(this::handleOnOver);
                this.component.setOnMouseExited(this::handleOnOut);
            }
        }

        private void handleOnOver(MouseEvent event) {
            this.component.setBlendMode(BlendMode.SCREEN);
        }

        private void handleOnOut(MouseEvent event) {
            this.component.setBlendMode(BlendMode.SRC_ATOP);
        }
    }
}
