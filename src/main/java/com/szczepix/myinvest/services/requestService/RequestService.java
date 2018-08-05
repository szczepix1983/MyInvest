package com.szczepix.myinvest.services.requestService;

import com.szczepix.myinvest.services.futureService.FutureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    private FutureService futureService;

    public void send(final BaseRequest request) {
        futureService.submit(request);
    }
}
