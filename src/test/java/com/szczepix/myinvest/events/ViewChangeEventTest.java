package com.szczepix.myinvest.events;

import com.szczepix.myinvest.enums.AppViewType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ViewChangeEventTest {

    private ViewChangeEvent event;

    @Before
    public void setUp() {
        event = new ViewChangeEvent(AppViewType.MAIN);
    }

    @Test
    public void creation() {
        assertThat(event).isNotNull();
    }

    @Test
    public void getAppViewType() {
        assertThat(event.getAppViewType()).isNotNull();
        assertThat(event.getAppViewType()).isEqualTo(AppViewType.MAIN);
    }
}