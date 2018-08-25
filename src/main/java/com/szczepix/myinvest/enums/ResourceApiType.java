package com.szczepix.myinvest.enums;

import lombok.Getter;

@Getter
public enum ResourceApiType {

    GOLD_PRICE("goldprice.org", "https://data-asg.goldprice.org/dbXRates/");

    String name;
    String url;

    ResourceApiType(final String name, final String url) {
        this.name = name;
        this.url = url;
    }
}

