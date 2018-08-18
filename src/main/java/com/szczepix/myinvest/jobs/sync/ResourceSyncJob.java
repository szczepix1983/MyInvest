package com.szczepix.myinvest.jobs.sync;

import com.szczepix.myinvest.jobs.BaseJob;
import com.szczepix.myinvest.models.SettingModel;
import com.szczepix.myinvest.services.futureService.IFuture;
import com.szczepix.myinvest.services.requestService.RequestService;
import com.szczepix.myinvest.services.requestService.goldprice.GoldPriceRatesRequest;
import com.szczepix.myinvest.services.requestService.goldprice.GoldPriceRatesResponse;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class ResourceSyncJob extends BaseJob {

    private final Logger LOG = Logger.getLogger(getClass().getName());

    private final RequestService requestService;
    private final GoldPriceRatesRequest request;
    private final SettingModel model;
    private final List<String> currencies = Arrays.asList("PLN", "USD");

    public ResourceSyncJob(final RequestService requestService, final SettingModel model) {
        super(new AtomicInteger(model.getEntity().getResourceSyncInterval()));
        this.requestService = requestService;
        this.model = model;
        this.request = new GoldPriceRatesRequest(this, "onComplete", currencies);
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
