package com.szczepix.myinvest.services.marketService;

import lombok.Getter;

@Getter
public class MarketItem {

    private final String name;
    private final double value;
    private final String currency;

    public MarketItem(final String name, double value, String currency) {
        this.name = name;
        this.value = value;
        this.currency = currency;
    }
}
