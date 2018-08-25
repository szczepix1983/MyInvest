package com.szczepix.myinvest.models;

import com.szczepix.myinvest.entities.WalletEntity;
import com.szczepix.myinvest.enums.PeriodType;
import com.szczepix.myinvest.enums.TargetType;
import com.szczepix.myinvest.events.WalletChangeEvent;
import com.szczepix.myinvest.services.eventService.EventService;
import lombok.Getter;

import java.util.TreeMap;

public class WalletModel {

    @Getter
    private final WalletEntity entity;

    private final EventService eventService;

    @Getter
    private WalletStats stats = new WalletStats();

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

    public void setStats(final WalletStats stats) {
        this.stats = stats;
        eventService.dispatch(changeEvent);
    }

    public static class WalletStats extends TreeMap<String, Double> {

        public WalletStats(){
            put("money", 0.0);
            put("percentage", 0.0);
        }
    }
}
