package com.szczepix.myinvest.services.futureService;

import com.szczepix.myinvest.requests.TestRequest;
import com.szczepix.myinvest.requests.TestResponse;
import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = FutureServiceTest.FutureServiceTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class FutureServiceTest {

    @Autowired
    private FutureService futureService;

    private CountDownLatch countdownLatch;
    private TestResponse response;
    private TestRequest request;
    private JFXPanel fxPanel;

    @Before
    public void setUp() {
        fxPanel = new JFXPanel();
        request = new TestRequest(this, "onComplete");
        countdownLatch = new CountDownLatch(4);
        countdownLatch.countDown();
    }

    @Test
    public void getExecutor() throws Exception {
        assertThat(futureService.getExecutor()).isNotNull();
    }

    @Test
    public void submit() throws Exception {
        futureService.submit(request);
        countdownLatch.await(10, TimeUnit.SECONDS);
        assertThat(response).isNotNull();
        assertThat(response.content).isNotNull();
        assertThat(request.completeCount).isEqualTo(1);
    }

    @Test
    public void submitNull() throws Exception {
        futureService.submit(null);
        countdownLatch.await(1, TimeUnit.SECONDS);
    }

    public void onComplete(final TestResponse response) {
        this.response = response;
    }

    @Configuration
    static class FutureServiceTestConfiguration {

        @Bean
        public FutureService getFutureService() {
            return new FutureService();
        }
    }
}