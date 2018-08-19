package com.szczepix.myinvest.services.requestService;

import com.szczepix.myinvest.requests.TestRequest;
import com.szczepix.myinvest.services.futureService.FutureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = RequestServiceTest.RequestServiceTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class RequestServiceTest {

    @Autowired
    private IRequestService requestService;

    @Autowired
    private FutureService futureService;

    @Test
    public void send() throws Exception {
        TestRequest request = mock(TestRequest.class);
        requestService.send(request);
        verify(futureService, times(1)).submit(eq(request));
    }

    @Configuration
    static class RequestServiceTestConfiguration {

        @Bean
        public IRequestService requestService() {
            return new RequestService(futureService());
        }

        @Bean
        public FutureService futureService() {
            return mock(FutureService.class);
        }
    }
}