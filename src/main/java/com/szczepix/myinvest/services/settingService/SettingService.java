package com.szczepix.myinvest.services.settingService;

import com.szczepix.myinvest.dao.ISettingRepository;
import com.szczepix.myinvest.entities.SettingEntity;
import com.szczepix.myinvest.jobs.IJobFactory;
import com.szczepix.myinvest.models.SettingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SettingService implements ISettingService {

    private final Logger LOG = Logger.getLogger(getClass().getName());
    private final Integer ID = 1;
    private final Integer DEFAULT_RESOURCE_SYNC_INTERVAL = 10;

    private final ISettingRepository repository;
    private final SettingCache cache;
    private final IJobFactory jobFactory;

    @Autowired
    public SettingService(final ISettingRepository repository, final SettingCache cache, final IJobFactory jobFactory){
        this.repository = repository;
        this.cache = cache;
        this.jobFactory = jobFactory;
    }

    @PostConstruct
    public void init() {
        createIfNotExists();
        updateJobs();
    }

    private void createIfNotExists() {
        if (Objects.isNull(repository.findOne(ID))) {
            SettingEntity entity = new SettingEntity();
            entity.setResourceSyncApi("https://data-asg.goldprice.org/dbXRates/");
            entity.setResourceSyncInterval(DEFAULT_RESOURCE_SYNC_INTERVAL);
            repository.save(entity);
        }
        updateCache();
    }

    private void updateJobs() {
        jobFactory.createResourceSyncJob(getSettings());
    }

    private void updateCache() {
        cache.update(StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(SettingModel::new)
                .collect(Collectors.toList()));
    }

    public SettingModel getSettings() {
        return cache.getById(ID);
    }
}

