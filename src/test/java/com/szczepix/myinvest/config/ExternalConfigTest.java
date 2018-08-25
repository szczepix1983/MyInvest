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
@ContextConfiguration(classes = ExternalConfigTest.ExternalConfigTestConfiguration.class)
@TestPropertySource(locations = {"classpath:external.properties"})
public class ExternalConfigTest {

    @Autowired
    private IExternalConfig config;

    @Test
    public void getCurrencies() {
        assertThat(config.getCurrencies()).isNotNull();
        assertThat(config.getCurrencies().size()).isEqualTo(1);
    }

    @Configuration
    static class ExternalConfigTestConfiguration {

        @Bean
        public IExternalConfig config() {
            return new ExternalConfig();
        }
    }
}