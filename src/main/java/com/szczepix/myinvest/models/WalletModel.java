package com.szczepix.myinvest.models;

import com.szczepix.myinvest.entities.WalletEntity;
import lombok.Data;

@Data
public class WalletModel {

    private final WalletEntity entity;

    public WalletModel(final WalletEntity entity) {
        this.entity = entity;
    }
}
