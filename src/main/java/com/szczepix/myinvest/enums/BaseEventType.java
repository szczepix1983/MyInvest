package com.szczepix.myinvest.enums;

import lombok.Getter;

@Getter
public enum BaseEventType {

    MOCK("TEST_EVENT"),
    VIEW_CHANGE("VIEW_CHANGE"),
    WALLET_CHANGE("WALLET_CHANGE");

    String name;

    BaseEventType(final String name) {
        this.name = name;
    }
}
