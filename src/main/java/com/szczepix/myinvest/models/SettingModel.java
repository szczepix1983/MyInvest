package com.szczepix.myinvest.models;

import com.szczepix.myinvest.entities.SettingEntity;
import com.szczepix.myinvest.services.requestService.goldprice.GoldPriceRatesResponse;
import lombok.Getter;

@Getter
public class SettingModel {

    private final SettingEntity entity;

    private GoldPriceRatesResponse resourceContent;

    public SettingModel(final SettingEntity entity) {
        this.entity = entity;
    }

    public void update(GoldPriceRatesResponse response) {
        resourceContent = response;
    }
}
