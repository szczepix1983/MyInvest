package com.szczepix.myinvest.dao;

import com.szczepix.myinvest.entities.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfileRepository extends CrudRepository<ProfileEntity, Integer> {

    //    @Query("SELECT t FROM profiles t where t.id = ?1 AND t.password = ?2")
    ProfileEntity findProfileByUserNameAndPassword(String username, String password);
}

