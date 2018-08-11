package com.szczepix.myinvest.jobs;

import com.szczepix.myinvest.services.futureService.IFuture;

import java.util.concurrent.atomic.AtomicInteger;

public class BaseJobMock extends BaseJob {

    public int counter = 0;

    public BaseJobMock(final Object object, final String callback, final int fixedTime) {
        super(object, callback, new AtomicInteger(fixedTime));
    }

    public BaseJobMock(final int fixedTime) {
        super(new AtomicInteger(fixedTime));
    }

    @Override
    public IFuture submit() throws Exception {
        counter++;
        return super.submit();
    }
}
