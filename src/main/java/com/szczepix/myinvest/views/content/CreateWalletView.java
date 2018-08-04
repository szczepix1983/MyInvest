package com.szczepix.myinvest.views.content;

import com.szczepix.myinvest.views.FXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CreateWalletView extends FXMLView {

    @FXML
    public Button createButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(createButton, this::onHandleUpdate);
    }

    private void onHandleUpdate(ActionEvent event) {

    }
}

