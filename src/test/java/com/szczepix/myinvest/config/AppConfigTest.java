package com.szczepix.myinvest.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfigTest.AppConfigTestConfiguration.class)
@TestPropertySource(locations = {"classpath:test.properties"})
public class AppConfigTest {

    @Autowired
    private IAppConfig config;

    @Test
    public void getValue() {
        assertThat(config.getValue("external.value")).isNotNull();
        assertThat(config.getValue("external.value")).isEqualTo("My external test");
        assertThat(config.getValue("internal.value")).isNotNull();
        assertThat(config.getValue("internal.value")).isEqualTo("My internal test");
    }

    @Configuration
    static class AppConfigTestConfiguration {

        @Bean
        public IAppConfig config() {
            return new AppConfig();
        }
    }
}