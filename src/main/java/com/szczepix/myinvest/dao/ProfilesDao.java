package com.szczepix.myinvest.dao;

import com.szczepix.myinvest.entities.Profile;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ProfilesDao extends CrudRepository<Profile, Long> {

    Profile findProfileByUserNameAndPassword(String username, String password);
}