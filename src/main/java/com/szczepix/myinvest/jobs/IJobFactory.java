package com.szczepix.myinvest.jobs;


import com.szczepix.myinvest.models.SettingModel;
import com.szczepix.myinvest.models.WalletModel;

public interface IJobFactory {

    void createWalletJob(final WalletModel model);

    void createResourceSyncJob(final SettingModel settingModel);
}
