package com.szczepix.myinvest.utils;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class FxmlUtilsTest {

    private FxmlUtils fxmlUtils;

    private JFXPanel fxPanel;

    @Before
    public void setUp() {
        fxPanel = new JFXPanel();
        fxmlUtils = new FxmlUtils();
    }

    @Test
    public void creation() {
        assertThat(fxmlUtils).isNotNull();
    }

    @Test
    public void load() throws Exception {
        AnnotationConfigApplicationContext context = mock(AnnotationConfigApplicationContext.class);
        URL url = getClass().getClassLoader().getResource("mock.fxml");
        Parent parent = FxmlUtils.load(url, context);

        assertThat(parent).isNotNull();
    }

    @Test
    public void loadComponent() {
        try{
            URL url = getClass().getClassLoader().getResource("mockItem.fxml");
            FxmlUtils.load(url, new AnchorPane());
        } catch(RuntimeException e){
            fail("Unexpected exception has been thrown");
        }
    }

    @Test(expected = RuntimeException.class)
    public void loadComponentWithException() {
        URL url = getClass().getClassLoader().getResource("x.fxml");
        FxmlUtils.load(url, new AnchorPane());
    }

}