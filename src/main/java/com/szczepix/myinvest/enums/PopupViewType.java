package com.szczepix.myinvest.enums;

import lombok.Getter;

@Getter
public enum PopupViewType {

    EXCEPTION("Exception", "exception.fxml"),
    MOCK("Test", "mock.fxml");

    String title;
    String path;

    PopupViewType(final String title, final String path) {
        this.title = title;
        this.path = path;
    }
}
