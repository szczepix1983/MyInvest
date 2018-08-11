package com.szczepix.myinvest.services.profileService;

import com.szczepix.myinvest.models.ProfileModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

@Component
public class ProfilesCache {

    private final Logger LOG = Logger.getLogger(getClass().getName());

    private Map<Integer, ProfileModel> cacheMap = new TreeMap<>();
    private List<ProfileModel> cacheList = new ArrayList<>();

    public void update(final List<ProfileModel> list) {
        cacheMap.clear();
        list.forEach(profileModel -> cacheMap.put(profileModel.getEntity().getId(), profileModel));
        cacheList = list;
        LOG.info("Profiles: " + cacheList.size());
    }

    public ProfileModel getById(final Integer value) {
        return cacheMap.get(value);
    }

    public List<ProfileModel> getCache() {
        return cacheList;
    }
}