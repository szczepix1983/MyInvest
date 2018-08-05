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
@ContextConfiguration(classes = HibernateConfigTest.HibernateConfigTestConfiguration.class)
@TestPropertySource(locations = {"classpath:test.properties"})
public class HibernateConfigTest {

    @Autowired
    private HibernateConfig config;

    @Test
    public void dataSource() {
        assertThat(config.dataSource()).isNotNull();
    }

    @Test
    public void internalConfig() {
        assertThat(config.internalConfig).isNotNull();
    }

    @Test
    public void externalConfig() {
        assertThat(config.externalConfig).isNotNull();
    }

    @Test
    public void hibernateProperties() {
        assertThat(config.hibernateProperties()).isNotNull();
    }

    @Test
    public void entityManagerFactory() {
        assertThat(config.entityManagerFactory()).isNotNull();
    }

    @Configuration
    static class HibernateConfigTestConfiguration {

        @Bean
        public HibernateConfig hibernateConfig() {
            return new HibernateConfig();
        }

        @Bean
        public ExternalConfig externalConfig() {
            return new ExternalConfig();
        }

        @Bean
        public InternalConfig internalConfig() {
            return new InternalConfig();
        }
    }
}