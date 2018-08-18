package com.szczepix.myinvest.jobs.wallets;

import com.szczepix.myinvest.jobs.BaseJob;
import com.szczepix.myinvest.models.WalletModel;
import com.szczepix.myinvest.services.futureService.IFuture;
import com.szczepix.myinvest.utils.MathUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class UpdateMoneyJob extends BaseJob {

    private final Logger LOG = Logger.getLogger(getClass().getName());

    private final WalletModel model;

    public UpdateMoneyJob(final WalletModel model, final Integer fixedTime) {
        super(new AtomicInteger(fixedTime));
        this.model = model;
    }

    @Override
    public IFuture submit() throws Exception {
        LOG.info("MONEY: " + getMoney());
        model.setMoney(getMoney());

        return super.submit();
    }

    private double getMoney() {
        return MathUtils.calculateMoney(model.getEntity().getCreatedAt(), model.getEntity().getValue(), model.getPeriodType());
    }
}
