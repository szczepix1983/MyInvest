package com.szczepix.myinvest.enums;

import lombok.Getter;

@Getter
public enum PeriodType {

    HOURLY("per hour"),
    DAILY("per day"),
    MONTHLY("per month");

    String name;

    PeriodType(final String name) {
        this.name = name;
    }
}
