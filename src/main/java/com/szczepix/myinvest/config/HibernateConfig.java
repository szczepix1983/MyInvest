package com.szczepix.myinvest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfig {

    @Autowired
    public ExternalConfig externalConfig;

    @Autowired
    public InternalConfig internalConfig;

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.type(org.sqlite.SQLiteDataSource.class);
        dataSourceBuilder.url("jdbc:sqlite:" + externalConfig.getValue("db.name"));
        return dataSourceBuilder.build();
    }

    @Bean
    public Properties hibernateProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.dialect", internalConfig.getValue("spring.jpa.database-platform"));
        properties.put("hibernate.connection.driver_class", internalConfig.getValue("spring.datasource.driver-class-name"));
        properties.put("hibernate.hbm2ddl.auto", internalConfig.getValue("spring.jpa.hibernate.ddl-auto"));
        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setPackagesToScan("com.szczepix.myinvest.entities");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.afterPropertiesSet();
        return entityManagerFactoryBean;
    }
}
