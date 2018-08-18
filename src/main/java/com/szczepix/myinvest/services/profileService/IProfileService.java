package com.szczepix.myinvest.services.profileService;

import com.szczepix.myinvest.models.ProfileModel;

import java.util.List;

public interface IProfileService {

    void init();

    ProfileModel findProfileByUserNameAndPassword(String username, String password);

    List<ProfileModel> getProfiles();
}
