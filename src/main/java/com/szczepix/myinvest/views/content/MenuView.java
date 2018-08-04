package com.szczepix.myinvest.views.content;

import com.szczepix.myinvest.enums.ContentViewType;
import com.szczepix.myinvest.managers.StageManager;
import com.szczepix.myinvest.views.FXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.TextFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(profileButton, this::handleProfileButton);
        enableButton(walletsButton, this::handleWalletsButton);
    }

    private void handleProfileButton(ActionEvent actionEvent) {
        stageManager.show(ContentViewType.PROFILE, stageManager.getMainView().contentPane);
    }

    private void handleWalletsButton(ActionEvent actionEvent) {
        stageManager.show(ContentViewType.CREATE_WALLETS, stageManager.getMainView().contentPane);
    }
}
