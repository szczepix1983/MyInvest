package com.szczepix.myinvest.services.requestService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class BaseGetRequest<E> extends BaseRequest<E> {

    public BaseGetRequest(final Object scope, final String invokeMethod) {
        super(scope, invokeMethod);
    }

    E getContent() throws IOException {
        HttpGet request = new HttpGet(getPath());
        StringBuilder responseContent = new StringBuilder();
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        try (InputStream stream = entity.getContent()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Class<E> clazz = getTypeParameterClass();
        if (!clazz.equals(String.class))
            return objectMapper.readValue(responseContent.toString(), clazz);
        return (E) responseContent.toString();
    }
}
