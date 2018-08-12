package com.szczepix.myinvest.utils;

import com.szczepix.myinvest.enums.PeriodType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MathUtilsTest {

    private MathUtils mathUtils;

    @Before
    public void setUp() {
        mathUtils = new MathUtils();
    }

    @Test
    public void creation() {
        assertThat(mathUtils).isNotNull();
    }

    @Test
    public void calculateMoney() {
        long startTime = System.currentTimeMillis() - PeriodType.DAILY.getSeconds() * 1000;
        double result = MathUtils.calculateMoney(startTime, 100, PeriodType.DAILY);
        assertThat(result).isEqualTo(100.0);

        startTime = System.currentTimeMillis() - (PeriodType.DAILY.getSeconds() * 1000) / 2;
        result = MathUtils.calculateMoney(startTime, 100, PeriodType.DAILY);
        assertThat(result).isEqualTo(50.0);

        startTime = System.currentTimeMillis() - (PeriodType.DAILY.getSeconds() * 1000) / 10;
        result = MathUtils.calculateMoney(startTime, 100, PeriodType.DAILY);
        assertThat(result).isEqualTo(10.0);
    }

    @Test
    public void timeSpend() {
        long startTime = System.currentTimeMillis() - PeriodType.DAILY.getSeconds() * 1000;
        assertThat(MathUtils.timeSpend(startTime)).isEqualTo(PeriodType.DAILY.getSeconds() * 1000);

        startTime = System.currentTimeMillis() - (PeriodType.DAILY.getSeconds() * 1000) / 2;
        assertThat(MathUtils.timeSpend(startTime)).isEqualTo((PeriodType.DAILY.getSeconds() * 1000) / 2);

        startTime = System.currentTimeMillis() - (PeriodType.DAILY.getSeconds() * 1000) / 10;
        assertThat(MathUtils.timeSpend(startTime)).isEqualTo((PeriodType.DAILY.getSeconds() * 1000) / 10);
    }
}