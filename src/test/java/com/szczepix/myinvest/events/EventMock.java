package com.szczepix.myinvest.events;

import com.szczepix.myinvest.enums.BaseEventType;
import com.szczepix.myinvest.services.eventService.BaseEvent;

public class EventMock extends BaseEvent {

    private String data;

    public EventMock() {
        super(BaseEventType.MOCK);
    }

    public EventMock setData(final String data) {
        this.data = data;
        return this;
    }

    public String getData() {
        return data;
    }
}
