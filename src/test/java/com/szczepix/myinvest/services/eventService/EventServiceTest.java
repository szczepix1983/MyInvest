package com.szczepix.myinvest.services.eventService;

import com.szczepix.myinvest.enums.BaseEventType;
import com.szczepix.myinvest.events.EventMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = EventServiceTest.EventServiceTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    private String dispatchedMockData;

    @Test
    public void addListener() {
        eventService.addListener(BaseEventType.MOCK, this::checkMockListener);
        assertThat(dispatchedMockData).isNull();
    }

    @Test
    public void removeListener() {
        eventService.removeListener(BaseEventType.MOCK, this::checkMockListener);
        assertThat(dispatchedMockData).isNull();
    }

    @Test
    public void dispatchWhenNoListener() {
        eventService.dispatch(new EventMock());
    }

    @Test
    public void dispatchWithListener() {
        final EventMock eventMock = new EventMock().setData("szczepix");

        eventService.addListener(BaseEventType.MOCK, this::checkMockListener);
        eventService.dispatch(eventMock);

        assertThat(dispatchedMockData).isEqualTo(eventMock.getData());
    }

    @Test
    public void dispatchWithTwoListeners() {
        final EventMock eventMock1 = new EventMock().setData("szczepix 1");
        final EventMock eventMock2 = new EventMock().setData("szczepix 2");

        eventService.addListener(BaseEventType.MOCK, this::checkMockListener);
        eventService.dispatch(eventMock1);

        assertThat(dispatchedMockData).isEqualTo(eventMock1.getData());

        eventService.dispatch(eventMock2);

        assertThat(dispatchedMockData).isEqualTo(eventMock2.getData());
    }

    private void checkMockListener(final BaseEvent event) {
        EventMock eventMock = (EventMock) event;
        dispatchedMockData = eventMock.getData();
    }

    @Configuration
    static class EventServiceTestConfiguration {

        @Bean
        public EventService getEventService() {
            return new EventService();
        }
    }
}