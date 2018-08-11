package com.szczepix.myinvest.services.walletService;

import com.szczepix.myinvest.dao.WalletsRepository;
import com.szczepix.myinvest.entities.WalletEntity;
import com.szczepix.myinvest.models.WalletModel;
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

    @PostConstruct
    public void init() {
        updateCache();
    }

    private void updateCache() {
        cache.update(StreamSupport.stream(repository.findAll().spliterator(), false).map(WalletModel::new)
                .collect(Collectors.toList()));
    }

    public WalletModel create() {
        WalletEntity entity = new WalletEntity();
        WalletModel model = new WalletModel(entity);
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
