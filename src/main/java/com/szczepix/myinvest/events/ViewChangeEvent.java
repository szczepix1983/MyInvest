package com.szczepix.myinvest.events;

import com.szczepix.myinvest.enums.AppViewType;
import com.szczepix.myinvest.enums.BaseEventType;
import com.szczepix.myinvest.services.eventService.BaseEvent;
import lombok.Getter;

@Getter
public class ViewChangeEvent extends BaseEvent {

    private final AppViewType appViewType;

    public ViewChangeEvent(final AppViewType appViewType) {
        super(BaseEventType.VIEW_CHANGE);
        this.appViewType = appViewType;
    }
}
