package com.szczepix.myinvest.managers;

import com.szczepix.myinvest.enums.AppViewType;
import com.szczepix.myinvest.enums.ContentViewType;
import com.szczepix.myinvest.enums.PopupViewType;
import com.szczepix.myinvest.utils.FxmlUtils;
import com.szczepix.myinvest.views.MainView;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Objects;
import java.util.logging.Logger;

public class StageManager {

    private static final Logger LOG = Logger.getAnonymousLogger();

    @Autowired
    private AnnotationConfigApplicationContext context;

    private final Stage stage;

    private MainView view;

    public StageManager(final Stage stage) {
        this.stage = stage;
        this.stage.initStyle(StageStyle.DECORATED);
    }

    public void setMainView(final MainView view) {
        this.view = view;
    }

    public MainView getMainView() {
        return view;
    }

    public Stage getStage() {
        return stage;
    }

    public void show(final AppViewType view) {
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getPath());
        Scene scene = prepareScene(viewRootNodeHierarchy);

        stage.setTitle(view.getTitle());
        stage.setScene(scene);
        stage.setResizable(view.isResizeable());
        stage.sizeToScene();
        stage.centerOnScreen();
        try {
            stage.show();
        } catch (Exception exception) {
            logAndExit("Unable to show scene for title" + view.getTitle());
        }
    }

    public void show(final ContentViewType contentViewType, final Pane pane) {
        pane.getChildren().clear();
        pane.getChildren().add(loadViewNodeHierarchy(contentViewType.getPath()));
    }

    private Scene prepareScene(final Parent rootnode) {
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(rootnode);
        }
        scene.setRoot(rootnode);
        return scene;
    }

    private Parent loadViewNodeHierarchy(final String fxmlFilePath) {
        Parent rootNode = null;
        try {
            rootNode = FxmlUtils.load(getClass().getClassLoader().getResource(fxmlFilePath), context);
            Objects.requireNonNull(rootNode, "A Root FXML node must not be null");
        } catch (Exception exception) {
            LOG.warning(exception.toString());
            logAndExit("Unable to load FXML view: " + getClass().getClassLoader().getResource(fxmlFilePath));
        }
        return rootNode;
    }

    private void logAndExit(String errorMsg) {
        LOG.warning(errorMsg);
        Platform.exit();
    }

    public void showPopup(final PopupViewType popupViewType) {
        Popup popup = new Popup();
        popup.getContent().add(loadViewNodeHierarchy(popupViewType.getPath()));
    }


}
