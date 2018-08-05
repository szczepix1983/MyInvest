package com.szczepix.myinvest.services.requestService;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public abstract class BasePostRequest<E> extends BaseRequest<E> {

    final String USER_AGENT = "Mozilla/5.0";
    final HttpPost request;

    public BasePostRequest(Object scope, String invokeMethod) {
        super(scope, invokeMethod);

        request = new HttpPost(getPath());
        request.setHeader("User-Agent", USER_AGENT);
    }

    public BasePostRequest addParameter(final String params) {
        try{
            request.setEntity(new StringEntity(params));
        }catch(UnsupportedEncodingException uee){

        }
        return this;
    }

    public BasePostRequest addHeader(final String paramName, final String paramValue) {
        request.addHeader(paramName, paramValue);
        return this;
    }

    E getContent() throws IOException {
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(result.toString(), getTypeParameterClass());
    }
}
