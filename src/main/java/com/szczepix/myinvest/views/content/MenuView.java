package com.szczepix.myinvest.views.content;

import com.szczepix.myinvest.enums.ContentViewType;
import com.szczepix.myinvest.views.FXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.TextFlow;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MenuView extends FXMLView {

    @FXML
    public TextFlow infoText;
    @FXML
    public Button profileButton;
    @FXML
    public Button walletsButton;
    @FXML
    public Button settingsButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(profileButton, this::handleProfileButton);
        enableButton(walletsButton, this::handleWalletsButton);
        enableButton(settingsButton, this::handleSettingsButton);
    }

    protected void handleProfileButton(ActionEvent actionEvent) {
        stageManager.show(ContentViewType.PROFILE, stageManager.getView().contentPane);
    }

    protected void handleWalletsButton(ActionEvent actionEvent) {
        stageManager.show(ContentViewType.WALLETS, stageManager.getView().contentPane);
    }

    protected void handleSettingsButton(ActionEvent actionEvent) {
        stageManager.show(ContentViewType.SETTINGS, stageManager.getView().contentPane);
    }
}
