package com.szczepix.myinvest.enums;

public enum BaseEventType {

    MOCK("TEST_EVENT"),
    VIEW_CHANGE("VIEW_CHANGE");

    String name;

    BaseEventType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
