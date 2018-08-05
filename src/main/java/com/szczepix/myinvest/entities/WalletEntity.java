package com.szczepix.myinvest.entities;

import com.szczepix.myinvest.enums.WalletType;
import com.szczepix.myinvest.services.entityService.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Wallets")
@Data
public class WalletEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private WalletType walletType;
    private Long createdAt;
    
    @Override
    public String toString() {
        return print(id, name);
    }
}
