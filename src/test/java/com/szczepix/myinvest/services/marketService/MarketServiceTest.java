package com.szczepix.myinvest.services.marketService;

import com.szczepix.myinvest.services.eventService.EventService;
import com.szczepix.myinvest.services.requestService.goldprice.GoldPriceRatesResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ContextConfiguration(classes = MarketServiceTest.MarketServiceTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class MarketServiceTest {

    @Autowired
    private IMarketService marketService;

    private GoldPriceRatesResponse response;

    @Before
    public void setUp() {
        response = new GoldPriceRatesResponse();
        response.ts = 100L;
        response.tsj = 100L;
        response.date = "date";
        response.items = createMarketItems(Arrays.asList("PLN", "USD"));
    }

    @Test
    public void creation() {
        assertThat(marketService).isNotNull();
        assertThat(marketService.getGoldPrice().isPresent()).isFalse();
        assertThat(marketService.getSilverPrice().isPresent()).isFalse();
    }

    @Test
    public void update() {
        marketService.update(response, "USD");
        assertThat(marketService.getGoldPrice().isPresent()).isTrue();
        assertThat(marketService.getSilverPrice().isPresent()).isTrue();
    }

    @Test
    public void getGoldPrice() {
        marketService.update(response, "USD");
        assertThat(marketService.getGoldPrice().get().getValue()).isEqualTo(100);
        assertThat(marketService.getGoldPrice().get().getCurrency()).isEqualTo("USD");
        assertThat(marketService.getGoldPrice().get().getName()).isEqualTo("XAU");
    }

    @Test
    public void getSilverPrice() {
        marketService.update(response, "USD");
        assertThat(marketService.getSilverPrice().get().getValue()).isEqualTo(10);
        assertThat(marketService.getSilverPrice().get().getCurrency()).isEqualTo("USD");
        assertThat(marketService.getSilverPrice().get().getName()).isEqualTo("XAG");
    }

    @Test
    public void getMarketItemWhenCurrencyChange() {
        marketService.update(response, "USD");
        assertThat(marketService.getSilverPrice().get().getValue()).isEqualTo(10);
        assertThat(marketService.getSilverPrice().get().getCurrency()).isEqualTo("USD");
        assertThat(marketService.getSilverPrice().get().getName()).isEqualTo("XAG");
        assertThat(marketService.getGoldPrice().get().getValue()).isEqualTo(100);
        assertThat(marketService.getGoldPrice().get().getCurrency()).isEqualTo("USD");
        assertThat(marketService.getGoldPrice().get().getName()).isEqualTo("XAU");
        marketService.update(response, "PLN");
        assertThat(marketService.getSilverPrice().get().getValue()).isEqualTo(10);
        assertThat(marketService.getSilverPrice().get().getCurrency()).isEqualTo("PLN");
        assertThat(marketService.getSilverPrice().get().getName()).isEqualTo("XAG");
        assertThat(marketService.getGoldPrice().get().getValue()).isEqualTo(100);
        assertThat(marketService.getGoldPrice().get().getCurrency()).isEqualTo("PLN");
        assertThat(marketService.getGoldPrice().get().getName()).isEqualTo("XAU");
    }

    private List<GoldPriceRatesResponse.GoldPriceItem> createMarketItems(List<String> currencies) {
        List<GoldPriceRatesResponse.GoldPriceItem> list = new ArrayList<>();
        currencies.forEach(currency -> {
            list.add(createItem(currency));
        });
        return list;
    }

    private GoldPriceRatesResponse.GoldPriceItem createItem(String currency) {
        GoldPriceRatesResponse.GoldPriceItem item = new GoldPriceRatesResponse.GoldPriceItem();
        item.curr = currency;
        item.xagPrice = 10;
        item.xauPrice = 100;
        return item;
    }

    @Configuration
    static class MarketServiceTestConfiguration {

        @Bean
        public EventService eventService() {
            return mock(EventService.class);
        }

        @Bean
        public IMarketService getMarketService() {
            return new MarketService();
        }
    }
}