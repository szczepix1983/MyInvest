package com.szczepix.myinvest.models;

import com.szczepix.myinvest.entities.WalletEntity;
import com.szczepix.myinvest.enums.PeriodType;
import com.szczepix.myinvest.enums.TargetType;
import com.szczepix.myinvest.services.eventService.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WalletModelTest {

    private WalletModel walletModel;

    private EventService eventService;

    @Before
    public void setUp() {
        eventService = mock(EventService.class);
        WalletEntity entity = new WalletEntity();
        entity.setId(1);
        entity.setName("");
        entity.setPeriodType(PeriodType.MONTHLY.getName());
        walletModel = new WalletModel(entity, eventService);
        walletModel.getStats().put("money", 20.0);
        walletModel.setStats(walletModel.getStats());
    }

    @Test
    public void creation() {
        assertThat(walletModel).isNotNull();
        verify(eventService).dispatch(any());
    }

    @Test
    public void getPeriodType() {
        PeriodType[] types = PeriodType.class.getEnumConstants();
        for (PeriodType type : types) {
            walletModel.getEntity().setPeriodType(type.getName());
            assertThat(walletModel.getPeriodType()).isEqualTo(type);
        }
    }

    @Test(expected = RuntimeException.class)
    public void getWrongPeriodType() {
        walletModel.getEntity().setPeriodType("");
        walletModel.getPeriodType();
    }

    @Test
    public void getTargetType() {
        TargetType[] types = TargetType.class.getEnumConstants();
        for (TargetType type : types) {
            walletModel.getEntity().setTargetType(type.getName());
            assertThat(walletModel.getTargetType()).isEqualTo(type);
        }
    }

    @Test(expected = RuntimeException.class)
    public void getWrongTargetType() {
        walletModel.getEntity().setTargetType("");
        walletModel.getTargetType();
    }

    @Test
    public void getEntity() {
        assertThat(walletModel.getEntity()).isNotNull();
    }

    @Test
    public void getMoney() {
        assertThat(walletModel.getStats().get("money")).isEqualTo(20.0);
    }
}