package com.szczepix.myinvest.utils;

import com.szczepix.myinvest.enums.PeriodType;

import java.util.concurrent.TimeUnit;

public class MathUtils {

    public static double calculateMoney(final long startTime, final double value, final PeriodType periodType) {
        double moneyPerSecond = value / periodType.getSeconds();
        long timestampSeconds = TimeUnit.MILLISECONDS.toSeconds(timeSpend(startTime));
        return moneyPerSecond * timestampSeconds;
    }

    public static Long timeSpend(final long startTime) {
        return System.currentTimeMillis() - startTime;
    }
}
