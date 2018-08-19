package com.szczepix.myinvest.jobs;

import com.szczepix.myinvest.enums.TargetType;
import com.szczepix.myinvest.jobs.sync.ResourceSyncJob;
import com.szczepix.myinvest.jobs.wallets.UpdateGoldJob;
import com.szczepix.myinvest.jobs.wallets.UpdateMoneyJob;
import com.szczepix.myinvest.models.SettingModel;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.requestService.RequestService;
import com.szczepix.myinvest.services.schedulerService.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobFactory implements IJobFactory {

    private final SchedulerService schedulerService;
    private final RequestService requestService;

    @Autowired
    public JobFactory(final RequestService requestService, final SchedulerService schedulerService) {
        this.requestService = requestService;
        this.schedulerService = schedulerService;
    }

    public void createWalletJob(final WalletModel model) {
        if (model.getTargetType().equals(TargetType.MONEY))
            schedulerService.addJob(new UpdateMoneyJob(model, 5));
        else if (model.getTargetType().equals(TargetType.GOLD))
            schedulerService.addJob(new UpdateGoldJob(model, 10));
    }

    public void createResourceSyncJob(final SettingModel settingModel) {
        schedulerService.addJob(new ResourceSyncJob(requestService, settingModel));
    }
}
