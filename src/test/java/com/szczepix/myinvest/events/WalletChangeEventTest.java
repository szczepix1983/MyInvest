package com.szczepix.myinvest.events;

import com.szczepix.myinvest.models.WalletModel;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class WalletChangeEventTest {

    private WalletChangeEvent event;
    private WalletModel model;

    @Before
    public void setUp() {
        model = mock(WalletModel.class);
        event = new WalletChangeEvent(model);
    }

    @Test
    public void creation() {
        assertThat(event).isNotNull();
    }

    @Test
    public void getModel() {
        assertThat(event.getModel()).isNotNull();
        assertThat(event.getModel()).isEqualTo(model);
    }
}