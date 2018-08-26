package com.szczepix.myinvest.events;

import com.szczepix.myinvest.enums.BaseEventType;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.eventService.BaseEvent;
import lombok.Getter;

@Getter
public class WalletChangeEvent extends BaseEvent {

    private final WalletModel model;

    public WalletChangeEvent(final WalletModel model) {
        super(BaseEventType.WALLET_CHANGE);
        this.model = model;
    }
}

