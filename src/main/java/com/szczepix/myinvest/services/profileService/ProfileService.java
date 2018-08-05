package com.szczepix.myinvest.services.profileService;

import com.szczepix.myinvest.dao.ProfilesRepository;
import com.szczepix.myinvest.entities.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfilesRepository repository;

    public ProfileEntity findProfileByUserNameAndPassword(String username, String password) {
        return repository.findProfileByUserNameAndPassword(username, password);
    }
}
