package com.szczepix.myinvest.views;

import com.szczepix.myinvest.enums.ContentViewType;
import javafx.scene.layout.AnchorPane;
import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainViewTest extends FXMLViewTest<MainView> {

    @Override
    public Class<MainView> getTClass() {
        return MainView.class;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getView().contentPane = mock(AnchorPane.class);
        getView().menuPane = mock(AnchorPane.class);
    }

    @Test
    public void initialize() {
        getView().initialize(null, null);

        verify(getView().stageManager).setMainView(eq(getView()));
        verify(getView().stageManager).show(eq(ContentViewType.MENU), eq(getView().menuPane));
    }
}