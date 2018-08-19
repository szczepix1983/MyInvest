package com.szczepix.myinvest.views;

import com.szczepix.myinvest.enums.ContentViewType;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.eventService.EventService;
import com.szczepix.myinvest.services.walletService.IWalletService;
import com.szczepix.myinvest.views.components.WalletItemComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class WalletsView extends FXMLView {

    @FXML
    public TextField totalText;
    @FXML
    public Button createButton;
    @FXML
    public GridPane gridPane;

    @Autowired
    private IWalletService walletService;

    @Autowired
    private EventService eventService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(createButton, this::onCreateButton);
        fillContent();
    }

    private void fillContent() {
        totalText.setText(getTotalWallets());

        List<WalletModel> wallets = walletService.getWallets();
        for (int i = 0; i < wallets.size(); i++) {
            WalletModel walletModel = wallets.get(i);
            try {
                gridPane.add(new WalletItemComponent(walletModel, eventService).load(), 0, i);
            } catch (Exception e) {
                System.out.println("eeee: " + e);
            }
        }
    }

    private void onCreateButton(ActionEvent event) {
        stageManager.show(ContentViewType.CREATE_WALLETS, stageManager.getView().contentPane);
    }

    private String getTotalWallets() {
        return "" + walletService.getWallets().size();
    }
}
