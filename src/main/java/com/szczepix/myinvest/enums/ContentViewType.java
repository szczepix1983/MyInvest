package com.szczepix.myinvest.enums;

import lombok.Getter;

@Getter
public enum ContentViewType {

    MENU("Menu", "menu.fxml"),
    PROFILE("Profile", "profile.fxml"),
    CREATE_WALLETS("Profile", "create_wallet.fxml");

    String title;
    String path;

    ContentViewType(final String title, final String path) {
        this.title = title;
        this.path = path;
    }
}

