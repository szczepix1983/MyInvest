package com.szczepix.myinvest.config;

import com.szczepix.myinvest.managers.StageManager;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan("com.szczepix.myinvest")
@PropertySource("classpath:application.properties")
public class InternalConfig {

    @Autowired
    private Environment env;

    public String getValue(final String valueName) {
        return env.getProperty(valueName);
    }

//    @Autowired
//    private FutureService futureService;
//
//    @Bean
//    public TaskScheduler taskScheduler() {
//        return new SchedulerService();
//    }
//
//    @Bean
//    public Executor taskExecutor() {
//        return futureService.getExecutor();
//    }

    @Bean
    @Lazy
    public StageManager stageManager(final Stage stage) {
        return new StageManager(stage);
    }
}
