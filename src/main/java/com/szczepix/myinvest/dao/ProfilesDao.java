package com.szczepix.myinvest.dao;

import com.szczepix.myinvest.entities.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ProfilesDao extends CrudRepository<ProfileEntity, Long> {

    ProfileEntity findProfileByUserNameAndPassword(String username, String password);
}