package com.szczepix.myinvest.views.content;

import com.szczepix.myinvest.views.FXMLView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ActionsView extends FXMLView {

    @FXML
    public TextField companyIncome;
    @FXML
    public TextField companyPrice;
    @FXML
    public TextField priceEarningsResult;
    @FXML
    public TextField scoreResult;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableCompononet(companyIncome);
        enableCompononet(companyPrice);

        setupValueText();
    }

    private void setupValueText() {
        companyIncome.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                companyIncome.setText(oldValue);
            } else {
                priceEarningsResult.setText("" + getCompanyResult());
                scoreResult.setText(getScoreResult());
            }
        });

        companyPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                companyPrice.setText(oldValue);
            } else {
                priceEarningsResult.setText("" + getCompanyResult());
                scoreResult.setText(getScoreResult());
            }
        });
    }

    private float getCompanyPriceValue() {
        return companyPrice.getText().isEmpty() ? 0.0f : Float.parseFloat(companyPrice.getText());
    }

    private float getCompanyIncomeValue() {
        return companyIncome.getText().isEmpty() ? 0.0f : Float.parseFloat(companyIncome.getText());
    }

    private float getCompanyResult() {
        return getCompanyPriceValue()/getCompanyIncomeValue();
    }

    private String getScoreResult() {
        float companyResult = getCompanyResult();
        if (companyResult < 5)
            return "Super okazja";
        else if (companyResult < 8)
            return "Akcje tanie";
        else if (companyResult < 16)
            return "Akcje neutralne";
        else if (companyResult < 20)
            return "Akcje drogie";
        return "Bańka spekulacyjna";
    }
}

