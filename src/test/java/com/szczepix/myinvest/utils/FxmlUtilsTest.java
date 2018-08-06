package com.szczepix.myinvest.utils;

import javafx.scene.Parent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class FxmlUtilsTest {

    private FxmlUtils fxmlUtils;

    @Before
    public void setUp() {
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

}