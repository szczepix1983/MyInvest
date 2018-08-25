package com.szczepix.myinvest.jobs.sync;

import com.szczepix.myinvest.entities.SettingEntity;
import com.szczepix.myinvest.models.SettingModel;
import com.szczepix.myinvest.services.requestService.IRequestService;
import com.szczepix.myinvest.services.requestService.goldprice.GoldPriceRatesResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ResourceSyncJobTest {

    private ResourceSyncJob job;
    private SettingModel model;
    private SettingEntity entity;
    private IRequestService requestService;

    @Before
    public void setUp() {
        requestService = mock(IRequestService.class);
        entity = mock(SettingEntity.class);
        model = mock(SettingModel.class);
        when(model.getEntity()).thenReturn(entity);

        job = new ResourceSyncJob(requestService, model);
    }

    @Test
    public void submit() throws Exception {
        job.submit();
        verify(requestService, times(1)).send(any());
    }

    @Test
    public void onComplete() {
        GoldPriceRatesResponse response = mock(GoldPriceRatesResponse.class);
        job.onComplete(response);
        verify(model, times(1)).update(eq(response));
    }
}