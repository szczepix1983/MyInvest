package com.szczepix.myinvest.managers;

import com.szczepix.myinvest.enums.AppViewType;
import com.szczepix.myinvest.enums.ContentViewType;
import com.szczepix.myinvest.enums.PopupViewType;
import com.szczepix.myinvest.views.MainView;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = StageManagerTest.StageManagerTestConfiguration.class)
@TestPropertySource(locations = {"classpath:test.properties"})
@RunWith(SpringJUnit4ClassRunner.class)
public class StageManagerTest {

    @Autowired
    @Lazy
    private IStageManager stageManager;

    private JFXPanel fxPanel;

    @Before
    public void setUp() throws Exception {
        fxPanel = new JFXPanel();
    }

    @Test
    public void creation() {
        assertThat(stageManager).isNotNull();
        assertThat(stageManager.getStage()).isNotNull();
        assertThat(stageManager.getView()).isNotNull();
    }

    @Test
    public void showAppViewType() {
        try {
            stageManager.show(AppViewType.MOCK);
            assertThat(stageManager.getStage().getTitle()).isEqualTo(AppViewType.MOCK.getTitle());
            assertThat(stageManager.getStage().isResizable()).isEqualTo(AppViewType.MOCK.isResizeable());
        } catch (Exception e) {
            fail("Unexpected exception thrown by StageManager.show");
        }
    }

    @Test
    public void showContentViewType() {
        try {
            stageManager.show(ContentViewType.CREATE_INVESTMENT, stageManager.getView().contentPane);
            verify(stageManager.getView().contentPane.getChildren(), times(1)).clear();
            verify(stageManager.getView().contentPane.getChildren(), times(1)).add(any());
        } catch (Exception e) {
            fail("Unexpected exception thrown by StageManager.show");
        }
    }

    @Test
    public void showPopupViewType() throws Exception {
        try {
            stageManager.show(PopupViewType.MOCK);
        } catch (Exception e) {
            fail("Unexpected exception thrown by StageManager.show");
        }
    }

    @Configuration
    static class StageManagerTestConfiguration {

        @Bean
        public AnnotationConfigApplicationContext context() {
            return mock(AnnotationConfigApplicationContext.class);
        }

        @Bean
        public IStageManager stageManager() {
            Stage stage = mock(Stage.class);
            StageManager stageManager = new StageManager(stage);
            MainView mainView = mock(MainView.class);
            mainView.contentPane = mock(AnchorPane.class);
            mainView.menuPane = mock(AnchorPane.class);
            ObservableList childrens = mock(ObservableList.class);
            when(mainView.contentPane.getChildren()).thenReturn(childrens);
            stageManager.setView(mainView);
            return stageManager;
        }
    }
}