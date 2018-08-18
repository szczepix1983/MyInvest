package com.szczepix.myinvest.services.requestService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(MockitoJUnitRunner.class)
public class CallbackTest {

    private final Object data = new Object();
    private Callback callback;
    private CallbackMock mock;

    @Test
    public void invokeWithScope() {
        mock = new CallbackMock();
        callback = new Callback(mock, "onComplete");

        try {
            callback.invoke(data);
        } catch (Exception e) {
            fail("Exception thrown by callback object " + e);
        }
        assertThat(mock.parameters).isNotNull();
        assertThat(mock.parameters).isEqualTo(data);
    }

    @Test
    public void invokeWithoutScope() {
        callback = new Callback();

        try {
            callback.invoke(data);
        } catch (Exception e) {
            fail("Exception thrown by callback object " + e);
        }
    }

    static class CallbackMock {

        private Object parameters;

        public void onComplete(Object parameters) {
            this.parameters = parameters;
        }
    }
}