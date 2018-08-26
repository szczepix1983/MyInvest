package com.szczepix.myinvest.services.marketService;

import com.szczepix.myinvest.events.MarketChangeEvent;
import com.szczepix.myinvest.services.eventService.EventService;
import com.szczepix.myinvest.services.requestService.goldprice.GoldPriceRatesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MarketService implements IMarketService {

    private GoldPriceRatesResponse resourceContent;
    private Long lastUpdateTime = 0L;

    private Map<Long, MarketItem> cacheXAUMap = new TreeMap<>();
    private Map<Long, MarketItem> cacheXAGMap = new TreeMap<>();

    @Autowired
    private EventService eventService;

    @Override
    public void update(final GoldPriceRatesResponse response, final String currency) {
        resourceContent = response;
        Optional<GoldPriceRatesResponse.GoldPriceItem> item = resourceContent.items.stream().filter(goldPriceItem -> goldPriceItem.curr.equals(currency)).findFirst();
        if (item.isPresent()) {
            lastUpdateTime = resourceContent.ts;
            cacheXAUMap.put(lastUpdateTime, new MarketItem("XAU", item.get().xauPrice, currency));
            cacheXAGMap.put(lastUpdateTime, new MarketItem("XAG", item.get().xagPrice, currency));
            eventService.dispatch(new MarketChangeEvent(item.get().xauPrice, item.get().xagPrice));
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
