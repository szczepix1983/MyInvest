package com.szczepix.myinvest.views.content;

import com.szczepix.myinvest.enums.PeriodType;
import com.szczepix.myinvest.enums.TargetType;
import com.szczepix.myinvest.enums.WalletType;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.walletService.WalletService;
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
import java.util.ResourceBundle;

@Component
public class CreateSaveView extends FXMLView {

    @FXML
    public Button createButton;
    @FXML
    public TextField valueText;
    @FXML
    public TextField walletNameText;
    @FXML
    public ComboBox<PeriodType> periodCombobox;
    @FXML
    public ComboBox<TargetType> targetCombobox;

    @Autowired
    private WalletService walletService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(createButton, this::onCreateButton);
        enableCompononet(periodCombobox);
        enableCompononet(targetCombobox);
        enableCompononet(valueText);
        setupValueText();
        setupPeriodTypeCombobox();
        setupTargetTypeCombobox();
    }

    private void setupValueText() {
        valueText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                valueText.setText(oldValue);

            }
        });
    }

    private void setupTargetTypeCombobox() {
        targetCombobox.setItems(getTargetTypes());
        targetCombobox.setCellFactory(new Callback<ListView<TargetType>, ListCell<TargetType>>() {
            @Override
            public ListCell<TargetType> call(ListView<TargetType> p) {
                return new ListCell<TargetType>() {
                    @Override
                    protected void updateItem(TargetType item, boolean empty) {
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
        targetCombobox.setOnAction(this::onTargetComboboxChange);
    }

    private void setupPeriodTypeCombobox() {
        periodCombobox.setItems(getPeriodTypes());
        periodCombobox.setCellFactory(new Callback<ListView<PeriodType>, ListCell<PeriodType>>() {
            @Override
            public ListCell<PeriodType> call(ListView<PeriodType> p) {
                return new ListCell<PeriodType>() {
                    @Override
                    protected void updateItem(PeriodType item, boolean empty) {
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
        periodCombobox.setOnAction(this::onPeriodComboboxChange);
    }

    private void onPeriodComboboxChange(ActionEvent event) {
//        System.out.println(walletTypeCombobox.getValue());
    }

    private void onTargetComboboxChange(ActionEvent event) {
//        System.out.println(walletTypeCombobox.getValue());
    }

    private void onCreateButton(ActionEvent event) {
        try {
            WalletModel model = walletService.create();
            model.getEntity().setValue(Long.parseLong(valueText.getText()));
            model.getEntity().setName(walletNameText.getText());
            model.getEntity().setWalletType(WalletType.SAVE);
            model.getEntity().setCreatedAt(System.currentTimeMillis());
            model.getEntity().setPeriodType(periodCombobox.getValue());
            model.getEntity().setTargetType(targetCombobox.getValue());
            walletService.save(model);
        } catch (Exception e) {
            System.out.println("aaaaaaaaaa " + e);
        }
    }

    private ObservableList<PeriodType> getPeriodTypes() {
        PeriodType[] types = PeriodType.class.getEnumConstants();
        return FXCollections.observableArrayList(types);
    }

    private ObservableList<TargetType> getTargetTypes() {
        TargetType[] types = TargetType.class.getEnumConstants();
        return FXCollections.observableArrayList(types);
    }
}

