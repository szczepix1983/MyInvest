package com.szczepix.myinvest.services.marketService;

import com.szczepix.myinvest.services.requestService.goldprice.GoldPriceRatesResponse;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.logging.Logger;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MarketService implements IMarketService {

    private final Logger LOG = Logger.getLogger(getClass().getName());

    private GoldPriceRatesResponse resourceContent;
    private Long lastUpdateTime = 0L;

    private Map<Long, MarketItem> cacheXAUMap = new TreeMap<>();
    private Map<Long, MarketItem> cacheXAGMap = new TreeMap<>();

    @Override
    public void update(final GoldPriceRatesResponse response, final String currency) {
        resourceContent = response;
        Optional<GoldPriceRatesResponse.GoldPriceItem> item = resourceContent.items.stream().filter(goldPriceItem -> goldPriceItem.curr.equals(currency)).findFirst();
        if (item.isPresent()) {
            lastUpdateTime = resourceContent.ts;
            cacheXAUMap.put(lastUpdateTime, new MarketItem("XAU", item.get().xauPrice, currency));
            cacheXAGMap.put(lastUpdateTime, new MarketItem("XAG", item.get().xagPrice, currency));
        }
    }

    @Override
    public Optional<MarketItem> getGoldPrice() {
        return Optional.ofNullable(cacheXAUMap.get(lastUpdateTime));
    }

    @Override
    public Optional<MarketItem> getSilverPrice() {
        return Optional.ofNullable(cacheXAGMap.get(lastUpdateTime));
    }
}
