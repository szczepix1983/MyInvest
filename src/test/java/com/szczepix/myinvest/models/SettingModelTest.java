package com.szczepix.myinvest.models;

import com.szczepix.myinvest.entities.SettingEntity;
import com.szczepix.myinvest.services.requestService.goldprice.GoldPriceRatesResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SettingModelTest {

    private SettingModel settingModel;

    @Before
    public void setUp() {
        SettingEntity entity = new SettingEntity();
        entity.setId(1);
        entity.setResourceSyncApi("api");
        entity.setResourceSyncInterval(10);
        settingModel = new SettingModel(entity);
    }

    @Test
    public void creation() {
        assertThat(settingModel).isNotNull();
    }

    @Test
    public void getEntity() {
        assertThat(settingModel.getEntity()).isNotNull();
    }

    @Test
    public void update() {
        assertThat(settingModel.getResourceContent()).isNull();
        settingModel.update(Mockito.mock(GoldPriceRatesResponse.class));
        assertThat(settingModel.getResourceContent()).isNotNull();
    }
}