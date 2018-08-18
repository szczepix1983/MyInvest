package com.szczepix.myinvest.views;

import com.szczepix.myinvest.managers.StageManager;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FXMLViewTest.FXMLViewTestConfiguration.class)
public abstract class FXMLViewTest<T extends FXMLView> {

    @Autowired
    private StageManager stageManager;

    private FXMLView view;
    private JFXPanel fxPanel;

    public abstract Class<T> getTClass();

    public T getView() {
        return (T) view;
    }

    @Before
    public void setUp() throws Exception {
        fxPanel = new JFXPanel();
        view = getTClass().newInstance();
        view.stageManager = stageManager;
    }

    @Test
    public void creation() {
        assertThat(view).isNotNull();
        assertThat(view.getClass()).isEqualTo(getTClass());
        assertThat(view.stageManager).isNotNull();
    }

    @Test
    public void enableCompononetWithoutButton() {
        view.enableCompononet(null);
    }

    @Test
    public void enableCompononetWithButton() {
        Control component = mock(Control.class);
        view.enableCompononet(component);
    }

    @Test
    public void enableWithButton() {
        Button button = new Button();
        view.enableButton(button, this::handleButton);
    }

    @Test
    public void enableWithoutButton() {
        view.enableButton(null, this::handleButton);
    }

    public void handleButton(ActionEvent actionEvent) {

    }

    @Configuration
    static class FXMLViewTestConfiguration {

        @Bean
        public AnnotationConfigApplicationContext context() {
            return mock(AnnotationConfigApplicationContext.class);
        }

        @Bean
        public StageManager stageManager() {
            StageManager stageManager = mock(StageManager.class);
            MainView mainView = mock(MainView.class);
            mainView.contentPane = mock(AnchorPane.class);
            mainView.menuPane = mock(AnchorPane.class);
            doReturn(mainView).when(stageManager).getMainView();
            return stageManager;
        }
    }
}