package com.szczepix.myinvest;

import com.szczepix.myinvest.config.InternalConfig;
import com.szczepix.myinvest.enums.AppViewType;
import com.szczepix.myinvest.managers.StageManager;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Application extends javafx.application.Application {

    private ConfigurableApplicationContext context;

    private AnnotationConfigApplicationContext springContext;

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(Application.class);
        springContext = new AnnotationConfigApplicationContext(InternalConfig.class);
    }

    @Override
    public void start(Stage primaryStage) {
        StageManager stageManager = springContext.getBean(StageManager.class, primaryStage);
        stageManager.show(AppViewType.MAIN);
    }

    @Override
    public void stop() {
        context.stop();
    }


    public static void main(String[] args) {
        launch(Application.class, args);
    }
}
