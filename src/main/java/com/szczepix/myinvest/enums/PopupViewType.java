package com.szczepix.myinvest.enums;

public class PopupViewType {

    //Exception("Exception", "exception.fxml");

    String title;
    String path;

    PopupViewType(final String title, final String path) {
        this.title = title;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }
}
