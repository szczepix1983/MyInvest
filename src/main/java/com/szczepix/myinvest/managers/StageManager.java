package com.szczepix.myinvest.managers;

import com.szczepix.myinvest.enums.AppViewType;
import com.szczepix.myinvest.enums.ContentViewType;
import com.szczepix.myinvest.enums.PopupViewType;
import com.szczepix.myinvest.utils.FxmlUtils;
import com.szczepix.myinvest.views.MainView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Objects;
import java.util.logging.Logger;

public class StageManager implements IStageManager {

    private static final Logger LOG = Logger.getAnonymousLogger();
    @Getter
    private final Stage stage;
    @Autowired
    private AnnotationConfigApplicationContext context;
    @Getter
    @Setter
    private MainView view;

    public StageManager(final Stage stage) {
        this.stage = stage;
        this.stage.initStyle(StageStyle.DECORATED);
    }

    @Override
    public void show(final AppViewType view) {
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getPath());
        Scene scene = prepareScene(viewRootNodeHierarchy);

        stage.setTitle(view.getTitle());
        stage.setResizable(view.isResizeable());
        try {
            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.show();
        } catch (Exception exception) {
            LOG.warning("Unable to show scene for title" + view.getTitle());
        }
    }

    @Override
    public void show(final ContentViewType contentViewType, final Pane pane) {
        pane.getChildren().clear();
        pane.getChildren().add(loadViewNodeHierarchy(contentViewType.getPath()));
    }

    @Override
    public void show(final PopupViewType popupViewType) {
        Popup popup = new Popup();
        popup.getContent().add(loadViewNodeHierarchy(popupViewType.getPath()));
    }

    private Scene prepareScene(final Parent rootnode) {
        Scene scene = getCurrentScene(rootnode);
        scene.setRoot(rootnode);
        return scene;
    }

    private Scene getCurrentScene(final Parent rootnode) {
        try {
            Scene scene = stage.getScene();
            if (scene == null) {
                scene = new Scene(rootnode);
            }
            return scene;
        } catch (Exception e) {
            return new Scene(rootnode);
        }
    }

    private Parent loadViewNodeHierarchy(final String fxmlFilePath) {
        Parent rootNode = null;
        try {
            rootNode = FxmlUtils.load(getClass().getClassLoader().getResource(fxmlFilePath), context);
            Objects.requireNonNull(rootNode, "A Root FXML node must not be null");
        } catch (Exception exception) {
            LOG.warning(exception.toString());
        }
        return rootNode;
    }
}
