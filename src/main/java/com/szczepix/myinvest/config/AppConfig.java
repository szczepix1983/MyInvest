package com.szczepix.myinvest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AppConfig implements IAppConfig {

    @Autowired
    private Environment env;

    public String getValue(final String valueName) {
        return env.getProperty(valueName);
    }
}
