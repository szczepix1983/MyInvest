package com.szczepix.myinvest.config;

import javafx.stage.Stage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = InternalConfigTest.InternalConfigTestConfiguration.class)
@TestPropertySource(locations = {"classpath:test.properties"})
public class InternalConfigTest {

    @Autowired
    private InternalConfig config;

    @Test
    public void getValue() {
        assertThat(config.getValue("internal.value")).isNotNull();
        assertThat(config.getValue("internal.value")).isEqualTo("My internal test");
    }

    @Test
    public void stageManager() {
        assertThat(config.stageManager(Mockito.mock(Stage.class))).isNotNull();
    }

    @Configuration
    static class InternalConfigTestConfiguration {

        @Bean
        public AnnotationConfigApplicationContext context() {
            return Mockito.mock(AnnotationConfigApplicationContext.class);
        }

        @Bean
        public InternalConfig config() {
            return new InternalConfig();
        }
    }
}