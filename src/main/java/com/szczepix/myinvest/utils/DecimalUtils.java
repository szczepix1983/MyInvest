package com.szczepix.myinvest.utils;

import java.text.DecimalFormat;

public class DecimalUtils {

    private static DecimalFormat decimalFormat = new DecimalFormat("#0.###");

    public static String format(final double value) {
        return decimalFormat.format(value);
    }
}
