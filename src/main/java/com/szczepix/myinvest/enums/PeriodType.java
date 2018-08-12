package com.szczepix.myinvest.enums;

import lombok.Getter;

@Getter
public enum PeriodType {

    HOURLY("per hour", (60 * 60)),
    DAILY("per day", (60 * 60 * 24)),
    MONTHLY("per month", (60 * 60 * 24 * 30));

    String name;
    Long seconds;

    PeriodType(final String name, final long seconds) {
        this.name = name;
        this.seconds = seconds;
    }
}
