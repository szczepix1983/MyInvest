package com.szczepix.myinvest.events;

import com.szczepix.myinvest.enums.BaseEventType;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.eventService.BaseEvent;
import lombok.Getter;

@Getter
public class SettingsChangeEvent extends BaseEvent {

    private final String currency;

    public SettingsChangeEvent(final String currency) {
        super(BaseEventType.SETTINGS_CHANGE);
        this.currency = currency;
    }
}

