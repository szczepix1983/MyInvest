package com.szczepix.myinvest.enums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AppViewTypeTest {

    @Test
    public void getTitle() throws Exception {
        AppViewType[] types = AppViewType.class.getEnumConstants();
        for (AppViewType type : types) {
            assertThat(type.getTitle()).isNotNull();
            assertThat(type.getTitle()).isNotEmpty();
        }
    }

    @Test
    public void getPath() throws Exception {
        AppViewType[] types = AppViewType.class.getEnumConstants();
        for (AppViewType type : types) {
            assertThat(type.getPath()).isNotNull();
            assertThat(type.getPath()).isNotEmpty();
        }
    }

    @Test
    public void isResizeable() throws Exception {
        AppViewType[] types = AppViewType.class.getEnumConstants();
        for (AppViewType type : types) {
            assertThat(type.isResizeable()).isTrue();
        }
    }

}