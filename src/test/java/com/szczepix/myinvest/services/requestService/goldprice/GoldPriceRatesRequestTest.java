package com.szczepix.myinvest.services.requestService.goldprice;

import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GoldPriceRatesRequestTest {

    private GoldPriceRatesRequest request;
    private JFXPanel fxPanel;
    private CountDownLatch countdownLatch;
    private List<String> currencies = Arrays.asList("PLN", "USD");

    @Before
    public void setUp() {
        fxPanel = new JFXPanel();
        request = new GoldPriceRatesRequest(this, currencies);
        countdownLatch = new CountDownLatch(4);
        countdownLatch.countDown();
    }

    @Test
    public void getResponseWithoutSubmit() {
        assertThat(request.getResponse()).isNull();
    }

    @Test
    public void getResponseWithSubmit() throws Exception {
        request.submit();
        assertThat(request.getResponse()).isNotNull();
        assertThat(request.getResponse()).isInstanceOf(GoldPriceRatesResponse.class);
        countdownLatch.await(5, TimeUnit.SECONDS);
        checkResponse((GoldPriceRatesResponse) request.getResponse());
    }

    @Test
    public void getPath() {
        assertThat(request.getPath()).isNotNull();
        assertThat(request.getPath()).isEqualTo("https://data-asg.goldprice.org/dbXRates/PLN,USD");
    }

    private void checkResponse(final GoldPriceRatesResponse response) {
        assertThat(response.date).isNotNull();
        assertThat(response.date).isNotEmpty();
        assertThat(response.ts).isNotNull();
        assertThat(response.tsj).isNotNull();
        assertThat(response.items).isNotNull();
        assertThat(response.items.size()).isEqualTo(currencies.size());
        for (GoldPriceRatesResponse.GoldPriceItem item : response.items) {
            assertThat(item.chgXag).isNotNull();
            assertThat(item.chgXau).isNotNull();
            assertThat(item.curr).isNotNull();
            assertThat(item.pcXag).isNotNull();
            assertThat(item.pcXau).isNotNull();
            assertThat(item.xagClose).isNotNull();
            assertThat(item.xagPrice).isNotNull();
            assertThat(item.xauClose).isNotNull();
            assertThat(item.xauPrice).isNotNull();
        }
    }
}