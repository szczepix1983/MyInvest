package com.szczepix.myinvest.services.requestService;

import com.szczepix.myinvest.requests.TestResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BaseResponseTest {

    private TestResponse response;

    @Test
    public void checkToString() {
        response = new TestResponse("some response content");
        assertThat(response.toString()).isEqualTo("{\"content\":\"some response content\"}");
    }

}