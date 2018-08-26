package com.szczepix.myinvest.events;

import com.szczepix.myinvest.enums.BaseEventType;
import com.szczepix.myinvest.services.eventService.BaseEvent;
import lombok.Getter;

@Getter
public class MarketChangeEvent extends BaseEvent {

    private final double goldPrice;
    private final double silverPrice;

    public MarketChangeEvent(final double goldPrice, final double silverPrice) {
        super(BaseEventType.MARKET_CHANGE);
        this.goldPrice = goldPrice;
        this.silverPrice = silverPrice;
    }
}