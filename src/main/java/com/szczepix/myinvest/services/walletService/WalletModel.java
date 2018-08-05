package com.szczepix.myinvest.services.walletService;

import com.szczepix.myinvest.enums.PeriodType;
import com.szczepix.myinvest.enums.TargetType;
import com.szczepix.myinvest.enums.WalletType;
import lombok.Data;

@Data
public class WalletModel {

    private String name;
    private Long value;
    private WalletType walletType;
    private TargetType targetType;
    private PeriodType periodType;
}
