package com.szczepix.myinvest.jobs.wallets;

import com.szczepix.myinvest.entities.WalletEntity;
import com.szczepix.myinvest.enums.PeriodType;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.eventService.EventService;
import com.szczepix.myinvest.services.marketService.IMarketService;
import com.szczepix.myinvest.services.marketService.MarketItem;
import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateGoldJobTest {

    private UpdateGoldJob job;
    private WalletModel model;
    private WalletEntity entity;
    private IMarketService marketService;

    @Before
    public void setUp() {
        entity = mock(WalletEntity.class);
        when(entity.getPeriodType()).thenReturn(PeriodType.DAILY.getName());
        when(entity.getCreatedAt()).thenReturn(System.currentTimeMillis() - (60 * 60 * 1000));
        when(entity.getValue()).thenReturn(1000.0);

        marketService = mock(IMarketService.class);

        model = new WalletModel(entity, mock(EventService.class));
        model.getStats().put("money", 0.0);

        job = new UpdateGoldJob(model, marketService, 5);
    }

    @Test
    public void submitWithNoMarket() throws Exception {
        when(marketService.getGoldPrice()).thenReturn(Optional.empty());
        job.submit();
        assertThat(model.getStats().get("money")).isEqualTo(0);
    }

    @Test
    public void submitWithMarket() throws Exception {
        when(marketService.getGoldPrice()).thenReturn(createItem("USD"));
        job.submit();
        assertThat(model.getStats().get("money")).isEqualTo(41.6, Offset.offset(0.1));
    }

    private Optional<MarketItem> createItem(String currency) {
        MarketItem item = new MarketItem("XAU", 100, currency);
        return Optional.of(item);
    }

}