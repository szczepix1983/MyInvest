package com.szczepix.myinvest.services.settingService;

import com.szczepix.myinvest.models.SettingModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

@Component
public class SettingCache {

    private final Logger LOG = Logger.getLogger(getClass().getName());

    private Map<Integer, SettingModel> cacheMap = new TreeMap<>();
    private List<SettingModel> cacheList = new ArrayList<>();

    public void update(final List<SettingModel> list) {
        cacheMap.clear();
        list.forEach(model -> cacheMap.put(model.getEntity().getId(), model));
        cacheList = list;
        LOG.info("Setting: " + cacheList.size());
    }

    public SettingModel getById(final Integer value) {
        return cacheMap.get(value);
    }

    public List<SettingModel> getCache() {
        return cacheList;
    }
}