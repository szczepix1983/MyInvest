package com.szczepix.myinvest.services.walletService;

import com.szczepix.myinvest.dao.IWalletRepository;
import com.szczepix.myinvest.entities.WalletEntity;
import com.szczepix.myinvest.jobs.IJobFactory;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.eventService.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WalletService {

    private final IWalletRepository repository;
    private final WalletCache cache;
    private final EventService eventService;
    private final IJobFactory jobFactory;

    @Autowired
    public WalletService(final IWalletRepository repository, final WalletCache cache, final IJobFactory jobFactory, final EventService eventService) {
        this.repository = repository;
        this.cache = cache;
        this.jobFactory = jobFactory;
        this.eventService = eventService;
    }

    @PostConstruct
    public void init() {
        updateCache();
        updateJobs();
    }

    private void updateJobs() {
        cache.getCache().forEach(wallet -> jobFactory.createWalletJob(wallet));
    }

    private void updateCache() {
        cache.update(StreamSupport.stream(repository.findAll().spliterator(), false).map(entity -> new WalletModel(entity, eventService))
                .collect(Collectors.toList()));
    }

    public WalletModel create() {
        WalletEntity entity = new WalletEntity();
        WalletModel model = new WalletModel(entity, eventService);
        return model;
    }

    public void save(final WalletModel model) {
        repository.save(model.getEntity());
    }

    public WalletModel getWallet(final Integer id) {
        return cache.getById(id);
    }

    public List<WalletModel> getWallets() {
        return cache.getCache();
    }
}
