package com.szczepix.myinvest.models;

import com.szczepix.myinvest.entities.WalletEntity;
import com.szczepix.myinvest.enums.PeriodType;
import com.szczepix.myinvest.enums.TargetType;
import com.szczepix.myinvest.events.WalletChangeEvent;
import com.szczepix.myinvest.services.eventService.EventService;
import lombok.Getter;

public class WalletModel {

    @Getter
    private final WalletEntity entity;

    private final EventService eventService;

    @Getter
    private double money = 0.0;

    private WalletChangeEvent changeEvent = new WalletChangeEvent(this);

    public WalletModel(final WalletEntity entity, final EventService eventService) {
        this.entity = entity;
        this.eventService = eventService;
    }

    public PeriodType getPeriodType() {
        PeriodType[] types = PeriodType.class.getEnumConstants();
        for (PeriodType type : types) {
            if (type.getName().equals(entity.getPeriodType()))
                return type;
        }
        throw new RuntimeException("Can't find period type for wallet " + this);
    }

    public TargetType getTargetType() {
        TargetType[] types = TargetType.class.getEnumConstants();
        for (TargetType type : types) {
            if (type.getName().equals(entity.getTargetType()))
                return type;
        }
        throw new RuntimeException("Can't find target type for wallet " + this);
    }

    public void setMoney(final double money) {
        if (this.money != money) {
            this.money = money;
            eventService.dispatch(changeEvent);
        }
    }
}
