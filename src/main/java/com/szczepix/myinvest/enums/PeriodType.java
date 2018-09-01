package com.szczepix.myinvest.enums;

import lombok.Getter;

@Getter
public enum PeriodType {

    HOURLY("per hour", (60 * 60), (60 * 60 * 24)),
    DAILY("per day", (60 * 60 * 24), (60 * 60 * 24 * 30)),
    MONTHLY("per month", (60 * 60 * 24 * 30), (60 * 60 * 24 * 30 * 12));

    String name;
    Long duration;
    Long totalDuration;

    PeriodType(final String name, final long duration, final long totalDuration) {
        this.name = name;
        this.duration = duration;
        this.totalDuration = totalDuration;
    }
}
