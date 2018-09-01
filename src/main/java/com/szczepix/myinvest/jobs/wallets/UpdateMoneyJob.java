package com.szczepix.myinvest.jobs.wallets;

import com.szczepix.myinvest.jobs.BaseJob;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.futureService.IFuture;
import com.szczepix.myinvest.utils.MathUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class UpdateMoneyJob extends BaseJob {

    private final WalletModel model;

    public UpdateMoneyJob(final WalletModel model, final Integer fixedTime) {
        super(new AtomicInteger(fixedTime));
        this.model = model;
    }

    @Override
    public IFuture submit() throws Exception {
        updateStats();
        return super.submit();
    }

    private void updateStats() {
        WalletModel.WalletStats stats = model.getStats();
        final double money = MathUtils.calculateMoney(model.getEntity().getCreatedAt(), model.getEntity().getValue(), model.getPeriodType());
        final double percentage = MathUtils.calculatePercentage(model.getEntity().getCreatedAt(), model.getEntity().getValue(), model.getPeriodType());
        stats.put("money", money);
        stats.put("percentage", percentage);
        model.setStats(stats);
    }
}
