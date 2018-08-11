package com.szczepix.myinvest.jobs;

import com.szczepix.myinvest.services.futureService.IFuture;

public interface IBaseJob extends IFuture {
    boolean isReady();
}
