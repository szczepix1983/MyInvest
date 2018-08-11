package com.szczepix.myinvest.services.walletService;

import com.szczepix.myinvest.dao.WalletsRepository;
import com.szczepix.myinvest.entities.WalletEntity;
import com.szczepix.myinvest.models.WalletModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WalletService {

    @Autowired
    private WalletsRepository repository;

    public WalletModel create() {
        WalletEntity entity = new WalletEntity();
        WalletModel model = new WalletModel(entity);
        return model;
    }

    public void save(final WalletModel model) {
        repository.save(model.getEntity());
    }

    public WalletModel getWallet(final Long id) {
        return new WalletModel(repository.findOne(id));
    }

    public List<WalletModel> getWallets() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).map(WalletModel::new)
                .collect(Collectors.toList());
    }
}
