package com.szczepix.myinvest.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "wallets")
@Data
public class WalletEntity extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    private String name;
    private Double value;
    private String walletType;
    private String targetType;
    private String periodType;
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
