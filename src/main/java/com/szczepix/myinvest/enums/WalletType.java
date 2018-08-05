package com.szczepix.myinvest.enums;

import lombok.Getter;

@Getter
public enum WalletType {

    SAVE("save"),
    INVESTMENT("investment");

    String type;

    WalletType(final String type) {
        this.type = type;
    }
}
