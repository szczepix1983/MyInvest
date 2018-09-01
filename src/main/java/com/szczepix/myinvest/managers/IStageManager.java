package com.szczepix.myinvest.managers;

import com.szczepix.myinvest.enums.AppViewType;
import com.szczepix.myinvest.enums.ContentViewType;
import com.szczepix.myinvest.views.MainView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public interface IStageManager {

    Stage getStage();

    MainView getView();

    void setView(MainView mainView);

    void show(final AppViewType view);

    void show(final ContentViewType contentViewType, final Pane pane);
}
