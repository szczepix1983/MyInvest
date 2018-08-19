package com.szczepix.myinvest.services.walletService;

import com.szczepix.myinvest.models.WalletModel;

import java.util.List;

public interface IWalletService {

    void init();

    WalletModel create();

    void save(final WalletModel model);

    WalletModel getWallet(final Integer id);

    List<WalletModel> getWallets();
}
