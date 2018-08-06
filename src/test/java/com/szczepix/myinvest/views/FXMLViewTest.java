package com.szczepix.myinvest.views;

import com.szczepix.myinvest.managers.StageManager;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FXMLViewTest.FXMLViewTestConfiguration.class)
public abstract class FXMLViewTest<T extends FXMLView> {

    @Autowired
    private StageManager stageManager;

    private FXMLView view;

    public abstract Class<T> getTClass();

    public T getView() {
        return (T) view;
    }

    @Before
    public void setUp() throws Exception {
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
    public void enableCompononet() {
        //Control control = new Control();
        view.enableCompononet(null);
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