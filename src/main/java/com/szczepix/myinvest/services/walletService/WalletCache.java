package com.szczepix.myinvest.services.walletService;

import com.szczepix.myinvest.models.WalletModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

@Component
public class WalletCache {

    private final Logger LOG = Logger.getLogger(getClass().getName());

    private Map<Integer, WalletModel> cacheMap = new TreeMap<>();
    private List<WalletModel> cacheList = new ArrayList<>();

    public void update(final List<WalletModel> list) {
        cacheMap.clear();
        list.forEach(model -> cacheMap.put(model.getEntity().getId(), model));
        cacheList = list;
        LOG.info("Wallets: " + cacheList.size());
    }

    public WalletModel getById(final Integer value) {
        return cacheMap.get(value);
    }

    public List<WalletModel> getCache() {
        return cacheList;
    }
}
