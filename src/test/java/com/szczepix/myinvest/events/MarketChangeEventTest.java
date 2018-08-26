package com.szczepix.myinvest.events;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MarketChangeEventTest {

    private MarketChangeEvent event;

    @Before
    public void setUp() {
        event = new MarketChangeEvent(10.0, 100.0);
    }

    @Test
    public void getGoldPrice() {
        assertThat(event.getGoldPrice()).isEqualTo(10.0);
    }

    @Test
    public void getSilverPrice() {
        assertThat(event.getSilverPrice()).isEqualTo(100.0);
    }
}