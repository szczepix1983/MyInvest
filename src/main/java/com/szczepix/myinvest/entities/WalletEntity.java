package com.szczepix.myinvest.entities;

import com.szczepix.myinvest.enums.PeriodType;
import com.szczepix.myinvest.enums.TargetType;
import com.szczepix.myinvest.enums.WalletType;
import com.szczepix.myinvest.services.entityService.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "wallets")
@Data
public class WalletEntity extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    private String name;
    private Long value;
    private WalletType walletType;
    private TargetType targetType;
    private PeriodType periodType;
    private Long createdAt;

    @Override
    public String toString() {
        return print(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        WalletEntity wallet = (WalletEntity) obj;
        return super.equals(obj) || (getId() == wallet.getId()) && getName().equals(wallet.getName());
    }
}
