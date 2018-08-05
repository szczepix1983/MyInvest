package com.szczepix.myinvest.services.requestService;

import com.szczepix.myinvest.services.futureService.IFuture;
import javafx.application.Platform;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseRequest<E> extends Callback implements IFuture {

    final HttpClient client;
    protected E content;

    BaseRequest(final Object scope, final String invokeMethod) {
        super(scope, invokeMethod);
        client = HttpClientBuilder.create().build();
    }

    @Override
    public IFuture submit() throws Exception {
        content = getContent();
        complete();
        return this;
    }

    protected void complete() {
        Platform.runLater(() -> {
            try {
                invoke(getResponse());
            } catch (Exception ignored) {
            }
        });
    }

    Class<E> getTypeParameterClass() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        return (Class<E>) paramType.getActualTypeArguments()[0];
    }

    abstract E getContent() throws IOException;

    protected abstract BaseResponse getResponse();

    protected abstract String getPath();
}
