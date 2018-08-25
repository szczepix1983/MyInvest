package com.szczepix.myinvest.enums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ResourceApiTypeTest {

    @Test
    public void getName() {
        ResourceApiType[] types = ResourceApiType.class.getEnumConstants();
        for (ResourceApiType type : types) {
            assertThat(type.getName()).isNotNull();
            assertThat(type.getName()).isNotEmpty();
        }
    }

    @Test
    public void getUrl() {
        ResourceApiType[] types = ResourceApiType.class.getEnumConstants();
        for (ResourceApiType type : types) {
            assertThat(type.getUrl()).isNotNull();
            assertThat(type.getUrl()).isNotEmpty();
        }
    }
}