package com.szczepix.myinvest.jobs;

import com.szczepix.myinvest.enums.TargetType;
import com.szczepix.myinvest.jobs.sync.ResourceSyncJob;
import com.szczepix.myinvest.jobs.wallets.UpdateGoldJob;
import com.szczepix.myinvest.jobs.wallets.UpdateMoneyJob;
import com.szczepix.myinvest.jobs.wallets.UpdateSilverJob;
import com.szczepix.myinvest.models.SettingModel;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.marketService.IMarketService;
import com.szczepix.myinvest.services.requestService.IRequestService;
import com.szczepix.myinvest.services.schedulerService.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobFactory implements IJobFactory {

    private final SchedulerService schedulerService;
    private final IRequestService requestService;
    private final IMarketService marketService;

    @Autowired
    public JobFactory(final IRequestService requestService, final SchedulerService schedulerService, final IMarketService marketService) {
        this.requestService = requestService;
        this.schedulerService = schedulerService;
        this.marketService = marketService;
    }

    public void createWalletJob(final WalletModel model) {
        if (model.getTargetType().equals(TargetType.MONEY))
            schedulerService.addJob(new UpdateMoneyJob(model, 5));
        else if (model.getTargetType().equals(TargetType.GOLD))
            schedulerService.addJob(new UpdateGoldJob(model, marketService, 5));
        else if (model.getTargetType().equals(TargetType.SILVER))
            schedulerService.addJob(new UpdateSilverJob(model, marketService, 5));
    }

    public void createResourceSyncJob(final SettingModel settingModel) {
        schedulerService.addJob(new ResourceSyncJob(requestService, marketService, settingModel));
    }
}
