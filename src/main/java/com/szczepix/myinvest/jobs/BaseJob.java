package com.szczepix.myinvest.jobs;

import com.szczepix.myinvest.services.futureService.IFuture;
import com.szczepix.myinvest.services.requestService.Callback;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseJob extends Callback implements IBaseJob {

    private final AtomicInteger fixedTime;
    private AtomicInteger counter = new AtomicInteger(1);

    public BaseJob(final Object scope, final String invokeMethod, final AtomicInteger fixedTime) {
        super(scope, invokeMethod);
        this.fixedTime = fixedTime;
    }

    public BaseJob(final AtomicInteger fixedTime) {
        this.fixedTime = fixedTime;
    }

    @Override
    public IFuture submit() throws Exception {
        return this;
    }

    @Override
    public boolean isReady() {
        boolean isReady = counter.get() == fixedTime.get();
        counter.set(isReady ? 1 : counter.get() + 1);
        return isReady;
    }
}
