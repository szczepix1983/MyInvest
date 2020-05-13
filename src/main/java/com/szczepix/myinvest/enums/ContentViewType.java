package com.szczepix.myinvest.enums;

import lombok.Getter;

@Getter
public enum ContentViewType {

    MENU("Menu", "menu.fxml"),
    PROFILE("Profile", "profile.fxml"),
    WALLETS("Wallets", "wallets.fxml"),
    ANALYZE("Analyze", "actions.fxml"),
    SETTINGS("Settings", "settings.fxml"),
    CREATE_WALLETS("Profile", "create_wallet.fxml"),
    CREATE_INVESTMENT("Investments", "create_investment.fxml"),
    CREATE_SAVE("Saves", "create_save.fxml");

    String title;
    String path;

    ContentViewType(final String title, final String path) {
        this.title = title;
        this.path = path;
    }
}

