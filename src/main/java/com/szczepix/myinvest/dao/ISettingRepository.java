package com.szczepix.myinvest.dao;

import com.szczepix.myinvest.entities.SettingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISettingRepository extends CrudRepository<SettingEntity, Integer> {

}
