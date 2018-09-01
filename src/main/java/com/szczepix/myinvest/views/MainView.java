package com.szczepix.myinvest.views;

import com.szczepix.myinvest.enums.ContentViewType;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainView extends FXMLView {

    @FXML
    public AnchorPane menuPane;
    @FXML
    public AnchorPane contentPane;
    @FXML
    public StackPane popupPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stageManager.setView(this);
        stageManager.show(ContentViewType.MENU, menuPane);
    }
}
