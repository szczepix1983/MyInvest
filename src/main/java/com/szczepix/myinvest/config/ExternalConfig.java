package com.szczepix.myinvest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:externals.properties")
public class ExternalConfig {

    @Autowired
    private Environment env;

    public String getValue(final String valueName) {
        return env.getProperty(valueName);
    }
}

