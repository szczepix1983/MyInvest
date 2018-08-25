package com.szczepix.myinvest.services.marketService;

import com.szczepix.myinvest.services.requestService.goldprice.GoldPriceRatesResponse;

import java.util.Optional;

public interface IMarketService {

    void update(final GoldPriceRatesResponse response, final String currency);

    Optional<MarketItem> getGoldPrice();

    Optional<MarketItem> getSilverPrice();
}
