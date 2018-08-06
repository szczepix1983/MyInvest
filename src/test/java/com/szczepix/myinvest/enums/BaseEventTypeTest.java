package com.szczepix.myinvest.enums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BaseEventTypeTest {

    @Test
    public void getName() throws Exception {
        BaseEventType[] types = BaseEventType.class.getEnumConstants();
        for (BaseEventType type : types) {
            assertThat(type.getName()).isNotNull();
            assertThat(type.getName()).isNotEmpty();
        }
    }

}