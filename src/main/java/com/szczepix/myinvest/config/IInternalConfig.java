package com.szczepix.myinvest.config;

import com.szczepix.myinvest.managers.IStageManager;
import javafx.stage.Stage;
import org.springframework.scheduling.TaskScheduler;

import java.util.concurrent.Executor;

public interface IInternalConfig {

    String getDefaultCurrency();

    Integer getDefaultResourceInterval();

    TaskScheduler taskScheduler();

    Executor taskExecutor();

    IStageManager stageManager(final Stage stage);
}
