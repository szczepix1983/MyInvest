package com.szczepix.myinvest.services.requestService.goldprice;

import com.szczepix.myinvest.services.requestService.BaseResponse;

import java.util.List;

public class GoldPriceRatesResponse extends BaseResponse {

    public Long ts;
    public Long tsj;
    public String date;
    public List<GoldPriceItem> items;

    public GoldPriceRatesResponse() {
    }

    public static class GoldPriceItem {
        public String curr;
        public double xauPrice;
        public double xagPrice;
        public double chgXau;
        public double chgXag;
        public double pcXau;
        public double pcXag;
        public double xauClose;
        public double xagClose;
    }
}

