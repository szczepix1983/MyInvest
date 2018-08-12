package com.szczepix.myinvest.services.walletService;

import com.szczepix.myinvest.dao.WalletsRepository;
import com.szczepix.myinvest.entities.WalletEntity;
import com.szczepix.myinvest.jobs.wallets.UpdateWalletJob;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.eventService.EventService;
import com.szczepix.myinvest.services.schedulerService.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WalletService {

    private final Logger LOG = Logger.getLogger(getClass().getName());

    @Autowired
    private WalletsRepository repository;

    @Autowired
    private WalletCache cache;

    @Autowired
    private SchedulerService taskScheduler;

    @Autowired
    private EventService eventService;

    @PostConstruct
    public void init() {
        updateCache();
        updateJobs();
    }

    private void updateJobs() {
        cache.getCache().forEach(wallet -> taskScheduler.addJob(new UpdateWalletJob(wallet, 5)));
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
