package com.szczepix.myinvest.enums;

import lombok.Getter;

@Getter
public enum TargetType {

    GOLD("gold"),
    SILVER("silver"),
    MONEY("money");

    String name;

    TargetType(final String name) {
        this.name = name;
    }
}
