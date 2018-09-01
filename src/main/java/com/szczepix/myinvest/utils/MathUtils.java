package com.szczepix.myinvest.utils;

import com.szczepix.myinvest.enums.PeriodType;

import java.util.concurrent.TimeUnit;

public class MathUtils {

    public static double calculateMoney(final long startTime, final double value, final PeriodType periodType) {
        double moneyPerSecond = value / periodType.getDuration();
        long timestampSeconds = TimeUnit.MILLISECONDS.toSeconds(timeSpend(startTime));
        return moneyPerSecond * timestampSeconds;
    }

    public static double calculateTotalMoney(final double value, final PeriodType periodType) {
        double moneyPerSecond = value / periodType.getDuration();
        return moneyPerSecond * periodType.getTotalDuration();
    }

    public static Long timeSpend(final long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    public static double calculatePercentage(Long createdAt, Double value, PeriodType periodType) {
        return Math.min((calculateMoney(createdAt, value, periodType) / calculateTotalMoney(value, periodType)) * 100, 100);
    }
}
