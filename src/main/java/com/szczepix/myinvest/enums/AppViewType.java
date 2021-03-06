package com.szczepix.myinvest.enums;

import lombok.Getter;

@Getter
public enum AppViewType {

    //    LOGIN("Login", "login.fxml", false),
//    LOCK("Locked", "lock.fxml", false),
//    REGISTER("Register", "register.fxml", false),
    MAIN("Welcome", "main.fxml", true),
    MOCK("Test", "mock.fxml", true);

    String title;
    String path;
    boolean resizeable;

    AppViewType(final String title, final String path, final boolean resizeable) {
        this.title = title;
        this.path = path;
        this.resizeable = resizeable;
    }
}
