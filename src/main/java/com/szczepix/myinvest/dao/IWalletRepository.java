package com.szczepix.myinvest.dao;

import com.szczepix.myinvest.entities.WalletEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWalletRepository extends CrudRepository<WalletEntity, Integer> {

}
