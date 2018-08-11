package com.szczepix.myinvest.services.profileService;

import com.szczepix.myinvest.dao.ProfilesRepository;
import com.szczepix.myinvest.entities.ProfileEntity;
import com.szczepix.myinvest.models.ProfileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfilesRepository repository;

    public ProfileModel findProfileByUserNameAndPassword(String username, String password) {
        return new ProfileModel(repository.findProfileByUserNameAndPassword(username, password));
    }
}
