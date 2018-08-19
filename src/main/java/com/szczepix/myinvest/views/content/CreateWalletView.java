package com.szczepix.myinvest.views.content;

import com.szczepix.myinvest.enums.ContentViewType;
import com.szczepix.myinvest.enums.WalletType;
import com.szczepix.myinvest.services.walletService.IWalletService;
import com.szczepix.myinvest.views.FXMLView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CreateWalletView extends FXMLView {

    @FXML
    public ComboBox<WalletType> walletTypeCombobox;
    @FXML
    public TextField totalText;
    @FXML
    public AnchorPane typePane;

    @Autowired
    private IWalletService walletService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableCompononet(walletTypeCombobox);
        setupWalletTypeCombobox();
        totalText.setText(getTotalWallets());
    }

    private void setupWalletTypeCombobox() {
        walletTypeCombobox.setItems(getWalletTypes());
        walletTypeCombobox.setCellFactory(new Callback<ListView<WalletType>, ListCell<WalletType>>() {
            @Override
            public ListCell<WalletType> call(ListView<WalletType> p) {
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

    private void onWalletTyoeComboboxChange(ActionEvent event) {
        if (walletTypeCombobox.getValue().equals(WalletType.INVESTMENT)) {
            stageManager.show(ContentViewType.CREATE_INVESTMENT, typePane);
        } else {
            stageManager.show(ContentViewType.CREATE_SAVE, typePane);
        }
    }

    private ObservableList<WalletType> getWalletTypes() {
        WalletType[] walletTypes = WalletType.class.getEnumConstants();
        return FXCollections.observableArrayList(walletTypes);
    }

    private String getTotalWallets() {
        return "" + walletService.getWallets().size();
    }
}

