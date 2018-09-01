package com.szczepix.myinvest.views.popups;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.szczepix.myinvest.enums.PopupViewType;
import com.szczepix.myinvest.managers.IStageManager;
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

public abstract class BasePopupView extends AnchorPane implements Initializable {

    public abstract void onInitalize();

    private final IStageManager stageManager;

    private final PopupViewType popupViewType;

    protected JFXDialog dialog = new JFXDialog();

    public BasePopupView(final IStageManager stageManager, final PopupViewType popupViewType) {
        this.stageManager = stageManager;
        this.popupViewType = popupViewType;

        dialog.setDialogContainer(stageManager.getView().popupPane);
        dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
        dialog.setContent(load());
        dialog.setOnDialogOpened(this::onDialogOpen);
        dialog.setOnDialogClosed(this::onDialogClose);
    }

    private void onDialogClose(JFXDialogEvent jfxDialogEvent) {
        this.stageManager.getView().popupPane.setMouseTransparent(true);
    }

    private void onDialogOpen(JFXDialogEvent jfxDialogEvent) {
        this.stageManager.getView().popupPane.setMouseTransparent(false);
    }

    private BasePopupView load() {
        try {
            FxmlUtils.load(getClass().getClassLoader().getResource(popupViewType.getPath()), this);
        } catch (Exception ignored) {
        }
        return this;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
                    onInitalize();
                    dialog.show();
                }
        );
    }

    protected void close() {
        dialog.close();
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
