package com.szczepix.myinvest.views.content;

import com.szczepix.myinvest.config.IExternalConfig;
import com.szczepix.myinvest.config.IInternalConfig;
import com.szczepix.myinvest.enums.ResourceApiType;
import com.szczepix.myinvest.services.settingService.ISettingService;
import com.szczepix.myinvest.views.FXMLView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class SettingsView extends FXMLView {

    @FXML
    public Button saveButton;
    @FXML
    public TextField resourceIntervalText;
    @FXML
    public ComboBox<ResourceApiType> resourceApiCombobox;
    @FXML
    public ComboBox<String> currencyCombobox;

    @Autowired
    private ISettingService settingService;

    @Autowired
    private IExternalConfig externalConfig;

    @Autowired
    private IInternalConfig internalConfig;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(saveButton, this::onSaveButton);
        enableCompononet(resourceApiCombobox);
        enableCompononet(currencyCombobox);
        enableCompononet(resourceIntervalText);
        setupValueText();
        setupCurrencyCombobox();
        setupApiCombobox();
    }

    private void setupValueText() {
        resourceIntervalText.setText("" + settingService.getSettings().getEntity().getResourceSyncInterval());
        resourceIntervalText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                resourceIntervalText.setText(oldValue);
            }
        });
    }

    private void setupCurrencyCombobox() {
        currencyCombobox.setItems(getCurrencies());
        currencyCombobox.getSelectionModel().select(settingService.getSettings().getEntity().getCurrency());
        currencyCombobox.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> p) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            getStyleClass().add("my-list-cell");
                            setTextFill(Color.WHITE);
                            setBackground(new Background(new BackgroundFill(Color.rgb(33, 150, 243), CornerRadii.EMPTY, Insets.EMPTY)));
                        }
                    }
                };
            }
        });
    }

    private void setupApiCombobox() {
        resourceApiCombobox.setItems(getApis());
        resourceApiCombobox.getSelectionModel().select(settingService.getSettings().getResourceApiType());
        resourceApiCombobox.setCellFactory(new Callback<ListView<ResourceApiType>, ListCell<ResourceApiType>>() {
            @Override
            public ListCell<ResourceApiType> call(ListView<ResourceApiType> p) {
                return new ListCell<ResourceApiType>() {
                    @Override
                    protected void updateItem(ResourceApiType item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getName());
                            getStyleClass().add("my-list-cell");
                            setTextFill(Color.WHITE);
                            setBackground(new Background(new BackgroundFill(Color.rgb(33, 150, 243), CornerRadii.EMPTY, Insets.EMPTY)));
                        }
                    }
                };
            }
        });
    }

    private void onSaveButton(ActionEvent event) {
        try {
            settingService.getSettings().getEntity().setResourceSyncApi(resourceApiCombobox.getValue().getUrl());
            settingService.getSettings().getEntity().setCurrency(currencyCombobox.getValue());
            settingService.getSettings().getEntity().setResourceSyncInterval(Integer.parseInt(resourceIntervalText.getText()));
            settingService.save();
        } catch (Exception e) {
            System.out.println("aaaaaaaaaa " + e);
        }
    }

    private ObservableList<String> getCurrencies() {
        List<String> currencyList = new ArrayList<>();
        currencyList.add(internalConfig.getDefaultCurrency());
        currencyList.addAll(externalConfig.getCurrencies());
        return FXCollections.observableArrayList(currencyList.stream().sorted().collect(Collectors.toList()));
    }

    private ObservableList<ResourceApiType> getApis() {
        ResourceApiType[] apiTypes = ResourceApiType.class.getEnumConstants();
        return FXCollections.observableArrayList(apiTypes);
    }
}

