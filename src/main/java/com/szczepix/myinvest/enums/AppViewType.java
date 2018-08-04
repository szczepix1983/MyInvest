package com.szczepix.myinvest.enums;

public enum AppViewType {

//    LOGIN("Login", "login.fxml", false),
//    LOCK("Locked", "lock.fxml", false),
//    REGISTER("Register", "register.fxml", false),
    MAIN("Welcome", "main.fxml", true);

    String title;
    String path;
    boolean resizeable;

    AppViewType(final String title, final String path, final boolean resizeable) {
        this.title = title;
        this.path = path;
        this.resizeable = resizeable;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }

    public boolean getResizeable() { return resizeable; }
}
