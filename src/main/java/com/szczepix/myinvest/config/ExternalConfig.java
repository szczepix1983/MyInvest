package com.szczepix.myinvest.config;

import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

@PropertySource("classpath:external.properties")
public class ExternalConfig extends AppConfig implements IExternalConfig {

    @Override
    public List<String> getCurrencies() {
        return Arrays.asList(getValue("external.currencies").split(","));
    }
}

