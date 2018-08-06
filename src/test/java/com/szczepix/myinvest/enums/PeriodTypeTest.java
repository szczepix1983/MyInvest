package com.szczepix.myinvest.enums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PeriodTypeTest {

    @Test
    public void getName() throws Exception {
        PeriodType[] types = PeriodType.class.getEnumConstants();
        for (PeriodType type : types) {
            assertThat(type.getName()).isNotNull();
            assertThat(type.getName()).isNotEmpty();
        }
    }
}