package com.szczepix.myinvest.services.requestService.goldprice;

import com.szczepix.myinvest.services.requestService.BaseGetRequest;
import com.szczepix.myinvest.services.requestService.BaseResponse;

import java.util.List;

public class GoldPriceRatesRequest extends BaseGetRequest<GoldPriceRatesResponse> {

    private final String currencies;

    public GoldPriceRatesRequest(final Object scope, final List<String> currencies) {
        super(scope, "");
        this.currencies = String.join(",", currencies);
    }

    @Override
    protected BaseResponse getResponse() {
        return content;
    }

    @Override
    public String getPath() {
        return "https://data-asg.goldprice.org/dbXRates/"+currencies;
    }
}
