package com.szczepix.myinvest.views.components;

import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(MockitoJUnitRunner.class)
public class BaseComponentViewTest {

    private ComponentViewMock view;
    private JFXPanel fxPanel;

    @Before
    public void setUp() {
        fxPanel = new JFXPanel();
        view = new ComponentViewMock();
    }

    @Test
    public void creation() {
        assertThat(view).isNotNull();
    }

    @Test
    public void load() {
        try {
            view.load();
        } catch (Exception e) {
            fail("Unexpected exception has been thrown");
        }
    }

    @Test
    public void path() {
        assertThat(view.path()).isEqualTo(getClass().getClassLoader().getResource("components/mockComponent.fxml"));
    }

    @Test
    public void checkInitialize() throws Exception {
        view.initialize(null, null);
        Thread.sleep(1000L);
        assertThat(view.initialized).isTrue();
    }

    static class ComponentViewMock extends BaseComponentView {

        private boolean initialized;

        @Override
        public URL path() {
            return getClass().getClassLoader().getResource("components/mockComponent.fxml");
        }

        @Override
        public void onInitalize() {
            initialized = true;
        }
    }
}