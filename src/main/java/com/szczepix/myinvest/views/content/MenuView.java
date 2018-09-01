package com.szczepix.myinvest.views.content;

import com.szczepix.myinvest.enums.BaseEventType;
import com.szczepix.myinvest.enums.ContentViewType;
import com.szczepix.myinvest.events.MarketChangeEvent;
import com.szczepix.myinvest.events.SettingsChangeEvent;
import com.szczepix.myinvest.services.eventService.BaseEvent;
import com.szczepix.myinvest.services.eventService.EventService;
import com.szczepix.myinvest.services.settingService.ISettingService;
import com.szczepix.myinvest.utils.DecimalUtils;
import com.szczepix.myinvest.views.FXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MenuView extends FXMLView {

    @FXML
    public Button profileButton;
    @FXML
    public Button walletsButton;
    @FXML
    public Button settingsButton;
    @FXML
    public Button licenseButton;
    @FXML
    public TextField goldPriceText;
    @FXML
    public TextField silverPriceText;
    @FXML
    public TextField currencyGoldText;
    @FXML
    public TextField currencySilverText;

    @Autowired
    private EventService eventService;

    @Autowired
    private ISettingService settingService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(profileButton, this::handleProfileButton);
        enableButton(walletsButton, this::handleWalletsButton);
        enableButton(settingsButton, this::handleSettingsButton);
        enableButton(licenseButton, this::handleOpenLicense);

        updateMarketPrices(0.0, 0.0);
        updatCurrency(settingService.getSettings().getEntity().getCurrency());

        eventService.addListener(BaseEventType.MARKET_CHANGE, this::onMarketChange);
        eventService.addListener(BaseEventType.SETTINGS_CHANGE, this::onSettingsChange);
    }

    private void onSettingsChange(BaseEvent baseEvent) {
        SettingsChangeEvent settingsChangeEvent = (SettingsChangeEvent) baseEvent;
        updatCurrency(settingsChangeEvent.getCurrency());
    }

    private void onMarketChange(BaseEvent baseEvent) {
        MarketChangeEvent marketChangeEvent = (MarketChangeEvent) baseEvent;
        updateMarketPrices(marketChangeEvent.getGoldPrice(), marketChangeEvent.getSilverPrice());
    }

    private void updateMarketPrices(final double goldPrice, final double silverPrice) {
        goldPriceText.setText(DecimalUtils.format(goldPrice));
        silverPriceText.setText(DecimalUtils.format(silverPrice));
    }

    private void updatCurrency(final String currency) {
        currencyGoldText.setText(currency);
        currencySilverText.setText(currency);
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

    protected void handleOpenLicense(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/szczepix1983/MyInvest/blob/master/LICENSE"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
