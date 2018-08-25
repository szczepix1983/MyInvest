package com.szczepix.myinvest.config;

import com.szczepix.myinvest.managers.IStageManager;
import com.szczepix.myinvest.managers.StageManager;
import com.szczepix.myinvest.services.futureService.FutureService;
import com.szczepix.myinvest.services.schedulerService.SchedulerService;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.Executor;

@EnableScheduling
@ComponentScan("com.szczepix.myinvest")
@PropertySource("classpath:internal.properties")
public class InternalConfig extends AppConfig implements IInternalConfig {

    @Autowired
    private FutureService futureService;

    @Override
    public String getDefaultCurrency() {
        return getValue("internal.defaultCurrency");
    }

    @Override
    public Integer getDefaultResourceInterval() {
        return Integer.parseInt(getValue("internal.defaultResourceInterval"));
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new SchedulerService();
    }

    @Bean
    public Executor taskExecutor() {
        return futureService.getExecutor();
    }

    @Bean
    @Lazy
    public IStageManager stageManager(final Stage stage) {
        IStageManager stageManager = new StageManager(stage);
        return stageManager;
    }
}
