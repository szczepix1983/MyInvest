package com.szczepix.myinvest.services.walletService;

import com.szczepix.myinvest.dao.WalletsRepository;
import com.szczepix.myinvest.entities.WalletEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WalletService {

    @Autowired
    private WalletsRepository repository;

    public void create(final WalletModel model) {
        WalletEntity entity = new WalletEntity();
        entity.setValue(model.getValue());
        entity.setName(model.getName());
        entity.setWalletType(model.getWalletType());
        entity.setCreatedAt(System.currentTimeMillis());
        entity.setPeriodType(model.getPeriodType());
        entity.setTargetType(model.getTargetType());
        repository.save(entity);
    }

    public WalletEntity getWallet(final Long id) {
        return repository.findOne(id);
    }

    public List<WalletEntity> getWallets() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
