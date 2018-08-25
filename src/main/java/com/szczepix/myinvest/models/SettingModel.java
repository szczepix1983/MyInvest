package com.szczepix.myinvest.models;

import com.szczepix.myinvest.entities.SettingEntity;
import com.szczepix.myinvest.enums.ResourceApiType;
import lombok.Getter;

@Getter
public class SettingModel {

    private final SettingEntity entity;

    public SettingModel(final SettingEntity entity) {
        this.entity = entity;
    }

    public ResourceApiType getResourceApiType() {
        ResourceApiType[] types = ResourceApiType.class.getEnumConstants();
        for (ResourceApiType type : types) {
            if (type.getUrl().equals(entity.getResourceSyncApi()))
                return type;
        }
        throw new RuntimeException("Can't find resource api type " + this);
    }
}
