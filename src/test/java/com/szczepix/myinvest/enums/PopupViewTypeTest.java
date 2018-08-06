package com.szczepix.myinvest.enums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PopupViewTypeTest {

    @Test
    public void getTitle() throws Exception {
        PopupViewType[] types = PopupViewType.class.getEnumConstants();
        for (PopupViewType type : types) {
            assertThat(type.getTitle()).isNotNull();
            assertThat(type.getTitle()).isNotEmpty();
        }
    }

    @Test
    public void getPath() throws Exception {
        PopupViewType[] types = PopupViewType.class.getEnumConstants();
        for (PopupViewType type : types) {
            assertThat(type.getPath()).isNotNull();
            assertThat(type.getPath()).isNotEmpty();
        }
    }

}