package com.szczepix.myinvest.views.content;

import com.szczepix.myinvest.enums.WalletType;
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
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CreateWalletView extends FXMLView {

    @FXML
    public Button createButton;
    @FXML
    public ComboBox<WalletType> walletTypeCombobox;
    @FXML
    public TextField totalText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(createButton, this::onHandleUpdate);

        setupWalletTypeCombobox();
        totalText.setText(getTotalWallets());
    }

    private void setupWalletTypeCombobox() {
        walletTypeCombobox.setItems(getWalletTypes());
        walletTypeCombobox.setCellFactory(new Callback<ListView<WalletType>, ListCell<WalletType>>() {
            @Override public ListCell<WalletType> call(ListView<WalletType> p) {
                return new ListCell<WalletType>() {
                    @Override
                    protected void updateItem(WalletType item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getType());
                            getStyleClass().add("my-list-cell");
                            setTextFill(Color.WHITE);
                            setBackground(new Background(new BackgroundFill(Color.rgb(33, 150, 243), CornerRadii.EMPTY, Insets.EMPTY)));
                        }
                    }
                };
            }
        });
        walletTypeCombobox.setOnAction(this::onWalletTyoeComboboxChange);
    }

    private void onHandleUpdate(ActionEvent event) {

    }

    private void onWalletTyoeComboboxChange(ActionEvent event) {
//        System.out.println(walletTypeCombobox.getValue());
    }

    private ObservableList<WalletType> getWalletTypes(){
        WalletType[] walletTypes = WalletType.class.getEnumConstants();
        return FXCollections.observableArrayList(walletTypes);
    }

    private String getTotalWallets() {
        return "1";
    }
}

