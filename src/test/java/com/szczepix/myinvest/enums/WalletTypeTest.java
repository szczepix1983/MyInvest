package com.szczepix.myinvest.enums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class WalletTypeTest {

    @Test
    public void getType() throws Exception {
        WalletType[] types = WalletType.class.getEnumConstants();
        for (WalletType type : types) {
            assertThat(type.getType()).isNotNull();
            assertThat(type.getType()).isNotEmpty();
        }
    }
}