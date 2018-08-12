package com.szczepix.myinvest.views.components;

import com.szczepix.myinvest.enums.BaseEventType;
import com.szczepix.myinvest.events.WalletChangeEvent;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.eventService.BaseEvent;
import com.szczepix.myinvest.services.eventService.EventService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class WalletItemView extends AnchorPane implements Initializable {

    @FXML
    public TextField nameText;
    @FXML
    public TextField valueText;
    @FXML
    public TextField moneyText;
    @FXML
    public Button deleteButton;

    private final DecimalFormat decimalFormat = new DecimalFormat("#0.###");

    private final WalletModel model;

    private final EventService eventService;

    public WalletItemView(final WalletModel model, final EventService eventService) {
        this.model = model;
        this.eventService = eventService;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.setLocation(getClass().getClassLoader().getResource(
                "components/walletItem.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameText.setText(model.getEntity().getName());
        valueText.setText("" + model.getEntity().getValue());
        setMoney(model.getMoney());
        eventService.addListener(BaseEventType.WALLET_CHANGE, this::onWalletChange);
    }

    private void onWalletChange(BaseEvent baseEvent) {
        WalletChangeEvent event = (WalletChangeEvent) baseEvent;
        if (event.getModel().equals(model)) {
            setMoney(model.getMoney());
        }
    }

    private void setMoney(final double money) {
        moneyText.setText(decimalFormat.format(money));
    }
}
