package com.szczepix.myinvest.views.content;

import com.szczepix.myinvest.enums.ContentViewType;
import com.szczepix.myinvest.views.FXMLViewTest;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MenuViewTest extends FXMLViewTest<MenuView> {

    @Override
    public Class<MenuView> getTClass() {
        return MenuView.class;
    }

    @Test
    public void handleProfileButton() {
        getView().handleProfileButton(null);
        verify(getView().stageManager, times(1)).show(eq(ContentViewType.PROFILE), any());
    }

    @Test
    public void handleWalletsButton() {
        getView().handleWalletsButton(null);
        verify(getView().stageManager, times(1)).show(eq(ContentViewType.WALLETS), any());
    }

    @Test
    public void initialize() {
        getView().initialize(null, null);
    }
}