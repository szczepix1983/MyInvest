package com.szczepix.myinvest.events;

import com.szczepix.myinvest.enums.AppViewType;
import com.szczepix.myinvest.enums.BaseEventType;
import com.szczepix.myinvest.services.eventService.BaseEvent;

public class ViewChangeEvent extends BaseEvent {

    private AppViewType appViewType;

    public ViewChangeEvent() {
        super(BaseEventType.VIEW_CHANGE);
    }

    public AppViewType getAppViewType() {
        return appViewType;
    }

    public ViewChangeEvent setAppViewType(final AppViewType appViewType) {
        this.appViewType = appViewType;
        return this;
    }
}
