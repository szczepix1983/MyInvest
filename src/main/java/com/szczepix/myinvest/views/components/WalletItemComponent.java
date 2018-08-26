package com.szczepix.myinvest.views.components;

import com.szczepix.myinvest.enums.BaseEventType;
import com.szczepix.myinvest.enums.TargetType;
import com.szczepix.myinvest.events.WalletChangeEvent;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.eventService.BaseEvent;
import com.szczepix.myinvest.services.eventService.EventService;
import com.szczepix.myinvest.utils.DecimalUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

import java.net.URL;

public class WalletItemComponent extends BaseComponentView {

    private final WalletModel model;
    private final EventService eventService;
    private final String currency;
    @FXML
    public TextField nameText;
    @FXML
    public TextField currencyText;
    @FXML
    public TextField valueText;
    @FXML
    public TextField moneyText;
    @FXML
    public TextField percentageText;
    @FXML
    public ProgressIndicator percentageIndicator;
    @FXML
    public Button detailsButton;

    public WalletItemComponent(final WalletModel model, final EventService eventService, final String currency) {
        this.model = model;
        this.eventService = eventService;
        this.currency = currency;
    }

    @Override
    public URL path() {
        return getClass().getClassLoader().getResource("components/walletItem.fxml");
    }

    @Override
    public void onInitalize() {
        nameText.setText(model.getEntity().getName());
        valueText.setText("" + model.getEntity().getValue());
        currencyText.setText(currency);
        setMoney(model.getStats().get("money"));
        if (model.getTargetType().equals(TargetType.SILVER) || model.getTargetType().equals(TargetType.GOLD)) {
            setPercentage(model.getStats().get("percentage"));
        } else {
            percentageIndicator.setVisible(false);
        }
        eventService.addListener(BaseEventType.WALLET_CHANGE, this::onWalletChange);

        //todo - initilize buttons later
        detailsButton.setDisable(true);
    }

    public void onWalletChange(BaseEvent baseEvent) {
        WalletChangeEvent event = (WalletChangeEvent) baseEvent;
        if (event.getModel().equals(model)) {
            setMoney(model.getStats().get("money"));
            if (model.getTargetType().equals(TargetType.SILVER) || model.getTargetType().equals(TargetType.GOLD)) {
                setPercentage(model.getStats().get("percentage"));
            }
        }
    }

    protected void setMoney(final double money) {
        moneyText.setText(DecimalUtils.format(money));
    }

    protected void setPercentage(final double percentage) {
        percentageIndicator.setProgress(percentage / 100);
        percentageText.setText(DecimalUtils.format(percentage));
    }
}
