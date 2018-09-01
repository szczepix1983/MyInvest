package com.szczepix.myinvest.views.components;

import com.szczepix.myinvest.utils.FxmlUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public abstract class BaseComponentView extends AnchorPane implements Initializable {

    public abstract URL path();

    public abstract void onInitalize();

    public BaseComponentView load() throws Exception {
        FxmlUtils.load(path(), this);
        return this;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(this::onInitalize);
    }

    protected void enableButton(final Button button, final EventHandler<ActionEvent> callback) {
        new InteractiveComponent(button);
        if (Objects.nonNull(button)) {
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
            if (Objects.nonNull(this.component)) {
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
