package com.szczepix.myinvest.jobs.wallets;

import com.szczepix.myinvest.jobs.BaseJob;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.futureService.IFuture;
import com.szczepix.myinvest.services.marketService.IMarketService;
import com.szczepix.myinvest.utils.MathUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class UpdateSilverJob extends BaseJob {

    private final WalletModel model;
    private final IMarketService marketService;

    public UpdateSilverJob(final WalletModel model, final IMarketService marketService, final Integer fixedTime) {
        super(new AtomicInteger(fixedTime));
        this.model = model;
        this.marketService = marketService;
    }

    @Override
    public IFuture submit() throws Exception {
        updateStats();
        return super.submit();
    }

    private void updateStats() {
        if (marketService.getSilverPrice().isPresent()) {
            WalletModel.WalletStats stats = model.getStats();
            final double money = MathUtils.calculateMoney(model.getEntity().getCreatedAt(), model.getEntity().getValue(), model.getPeriodType());
            final double percentage = Math.min((money / marketService.getSilverPrice().get().getValue()) * 100, 100);
            stats.put("money", money);
            stats.put("percentage", percentage);
            model.setStats(stats);
        }
    }
}
