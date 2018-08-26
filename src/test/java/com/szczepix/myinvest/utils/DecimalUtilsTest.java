package com.szczepix.myinvest.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DecimalUtilsTest {

    private DecimalUtils decimalUtils;

    @Before
    public void setUp() {
        decimalUtils = new DecimalUtils();
    }

    @Test
    public void creation() {
        assertThat(decimalUtils).isNotNull();
    }

    @Test
    public void format() {
        assertThat(DecimalUtils.format(0.00123)).isEqualTo("0.001");
        assertThat(DecimalUtils.format(190.001230001)).isEqualTo("190.001");
    }
}