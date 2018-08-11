package com.szczepix.myinvest.models;

import com.szczepix.myinvest.entities.ProfileEntity;
import lombok.Data;

@Data
public class ProfileModel {

    private final ProfileEntity entity;

    public ProfileModel(final ProfileEntity entity){
        this.entity = entity;
    }
}
