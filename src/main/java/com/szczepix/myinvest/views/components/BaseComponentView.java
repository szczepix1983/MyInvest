package com.szczepix.myinvest.views.components;

import com.szczepix.myinvest.utils.FxmlUtils;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class BaseComponentView extends AnchorPane implements Initializable {

    public abstract URL path();

    public abstract void onInitalize();

    public BaseComponentView load() {
        FxmlUtils.load(path(), this);
        return this;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(this::onInitalize);
    }
}
