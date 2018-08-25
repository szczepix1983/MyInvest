package com.szczepix.myinvest.jobs.sync;

import com.szczepix.myinvest.jobs.BaseJob;
import com.szczepix.myinvest.models.SettingModel;
import com.szczepix.myinvest.services.futureService.IFuture;
import com.szczepix.myinvest.services.requestService.IRequestService;
import com.szczepix.myinvest.services.requestService.goldprice.GoldPriceRatesRequest;
import com.szczepix.myinvest.services.requestService.goldprice.GoldPriceRatesResponse;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class ResourceSyncJob extends BaseJob {

    private final Logger LOG = Logger.getLogger(getClass().getName());

    private final IRequestService requestService;
    private final GoldPriceRatesRequest request;
    private final SettingModel model;

    public ResourceSyncJob(final IRequestService requestService, final SettingModel model) {
        super(new AtomicInteger(model.getEntity().getResourceSyncInterval()));
        this.requestService = requestService;
        this.model = model;
        this.request = new GoldPriceRatesRequest(this, "onComplete", Collections.singletonList(model.getEntity().getCurrency()));
    }

    @Override
    public IFuture submit() throws Exception {
        requestService.send(request);
        return super.submit();
    }

    public void onComplete(GoldPriceRatesResponse response) {
        LOG.info("response: " + response);
        this.model.update(response);
    }
}
