package com.szczepix.myinvest.models;

import com.szczepix.myinvest.entities.ProfileEntity;
import lombok.Getter;

public class ProfileModel {

    @Getter
    private final ProfileEntity entity;

    public ProfileModel(final ProfileEntity entity) {
        this.entity = entity;
    }
}
