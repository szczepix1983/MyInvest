package com.szczepix.myinvest;

import com.szczepix.myinvest.managers.StageManager;
import com.szczepix.myinvest.services.futureService.FutureService;
import com.szczepix.myinvest.services.schedulerService.SchedulerService;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Executor;

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
        assertThat(application.springContext).isNotNull();
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
            springContext = new AnnotationConfigApplicationContext(ApplicationTestsConfiguration.class);
        }
    }

    @Configuration
    static class ApplicationTestsConfiguration {

        @Bean
        public AnnotationConfigApplicationContext context() {
            return mock(AnnotationConfigApplicationContext.class);
        }

        @Bean
        public FutureService futureService() {
            FutureService futureService = mock(FutureService.class);
            Mockito.when(futureService.getExecutor()).thenReturn(mock(Executor.class));
            return futureService;
        }

        @Bean
        public TaskScheduler taskScheduler() {
            return mock(SchedulerService.class);
        }

        @Bean
        public Executor taskExecutor() {
            return mock(Executor.class);
        }

        @Bean
        public StageManager stageManager() {
            return mock(StageManager.class);
        }
    }
}
