package com.szczepix.myinvest.events;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SettingsChangeEventTest {

    private SettingsChangeEvent event;

    @Before
    public void setUp() {
        event = new SettingsChangeEvent("PLN");
    }

    @Test
    public void getCurrency() {
        assertThat(event.getCurrency()).isEqualTo("PLN");
    }
}