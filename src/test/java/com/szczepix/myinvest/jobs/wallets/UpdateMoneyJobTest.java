package com.szczepix.myinvest.jobs.wallets;

import com.szczepix.myinvest.entities.WalletEntity;
import com.szczepix.myinvest.enums.PeriodType;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.eventService.EventService;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateMoneyJobTest {

    private UpdateMoneyJob job;
    private WalletModel model;
    private WalletEntity entity;

    @Before
    public void setUp() {
        entity = mock(WalletEntity.class);
        when(entity.getPeriodType()).thenReturn(PeriodType.DAILY.getName());
        when(entity.getCreatedAt()).thenReturn(System.currentTimeMillis() - (60 * 60 * 1000));
        when(entity.getValue()).thenReturn(1000.0);

        model = new WalletModel(entity, mock(EventService.class));
        model.getStats().put("money", 0.0);
        model.getStats().put("percentage", 0.0);

        job = new UpdateMoneyJob(model, 5);
    }

    @Test
    public void submit() throws Exception {
        job.submit();
        assertThat(model.getStats().get("money")).isEqualTo(41.6, Offset.offset(0.1));
        assertThat(model.getStats().get("percentage")).isCloseTo(0.138, Offset.offset(0.001));
    }

}