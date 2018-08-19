package com.szczepix.myinvest.services.requestService;

import com.szczepix.myinvest.services.futureService.FutureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService implements IRequestService {

    private final FutureService futureService;

    @Autowired
    public RequestService(final FutureService futureService) {
        this.futureService = futureService;
    }

    public void send(final BaseRequest request) {
        futureService.submit(request);
    }
}
