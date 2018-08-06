package com.szczepix.myinvest.enums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TargetTypeTest {

    @Test
    public void getName() throws Exception {
        TargetType[] types = TargetType.class.getEnumConstants();
        for (TargetType type : types) {
            assertThat(type.getName()).isNotNull();
            assertThat(type.getName()).isNotEmpty();
        }
    }
}