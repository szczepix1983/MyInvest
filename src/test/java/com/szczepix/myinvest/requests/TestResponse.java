package com.szczepix.myinvest.requests;

import com.szczepix.myinvest.services.requestService.BaseResponse;

public class TestResponse extends BaseResponse {

    public final String content;

    public TestResponse(final String content) {
        this.content = content;
    }
}
