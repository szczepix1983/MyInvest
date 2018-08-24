package com.szczepix.myinvest;

import com.szczepix.myinvest.managers.IStageManager;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTests.ApplicationTestsConfiguration.class)
public class ApplicationTests {

    private ApplicationMock application;

    @Before
    public void setUp() {
        application = new ApplicationMock();
        application.init();
    }

    @Test
    public void creation() {
        assertThat(application).isNotNull();
    }

    @Test
    public void init() {
        assertThat(application.context).isNotNull();
    }

    @Test
    public void start() {
        application.start(mock(Stage.class));
    }

    @Test
    public void stop() {
        application.stop();
        verify(application.context, times(1)).stop();
    }

    static class ApplicationMock extends Application {

        @Override
        public void init() {
            context = mock(ConfigurableApplicationContext.class);
            doReturn(mock(IStageManager.class)).when(context).getBean(eq(IStageManager.class), any());
        }
    }

    @Configuration
    static class ApplicationTestsConfiguration {

    }
}
