package com.szczepix.myinvest.config;

import com.szczepix.myinvest.services.futureService.FutureService;
import com.szczepix.myinvest.services.schedulerService.SchedulerService;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Executor;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = InternalConfigTest.InternalConfigTestConfiguration.class)
@TestPropertySource(locations = {"classpath:internal.properties"})
public class InternalConfigTest {

    @Autowired
    private IInternalConfig config;

    @Test
    public void getDefaultCurrency() {
        assertThat(config.getDefaultCurrency()).isNotNull();
        assertThat(config.getDefaultCurrency()).isNotEmpty();
    }

    @Test
    public void getDefaultResourceInterval() {
        assertThat(config.getDefaultResourceInterval()).isNotNull();
        assertThat(config.getDefaultResourceInterval()).isGreaterThan(0);
    }

    @Test
    public void stageManager() {
        assertThat(config.stageManager(Mockito.mock(Stage.class))).isNotNull();
    }

    @Test
    public void taskScheduler() {
        assertThat(config.taskScheduler()).isNotNull();
    }

    @Test
    public void taskExecutor() {
        assertThat(config.taskExecutor()).isNotNull();
    }

    @Configuration
    static class InternalConfigTestConfiguration {

        @Bean
        public AnnotationConfigApplicationContext context() {
            return Mockito.mock(AnnotationConfigApplicationContext.class);
        }

        @Bean
        public FutureService futureService() {
            FutureService futureService = Mockito.mock(FutureService.class);
            Mockito.when(futureService.getExecutor()).thenReturn(Mockito.mock(Executor.class));
            return futureService;
        }

        @Bean
        public TaskScheduler taskScheduler() {
            return Mockito.mock(SchedulerService.class);
        }

        @Bean
        public Executor taskExecutor() {
            return Mockito.mock(Executor.class);
        }

        @Bean
        public IInternalConfig config() {
            return new InternalConfig();
        }
    }
}