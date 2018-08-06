package com.szczepix.myinvest.views;

import com.szczepix.myinvest.enums.ContentViewType;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainView extends FXMLView {

    @FXML
    public AnchorPane menuPane;
    @FXML
    public AnchorPane contentPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stageManager.setMainView(this);
        stageManager.show(ContentViewType.MENU, menuPane);
    }
}
