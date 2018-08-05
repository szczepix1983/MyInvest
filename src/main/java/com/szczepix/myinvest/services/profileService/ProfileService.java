package com.szczepix.myinvest.services.profileService;

import com.szczepix.myinvest.dao.ProfilesDao;
import com.szczepix.myinvest.entities.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfilesDao profilesDao;

    public ProfileEntity getProfile() {
//        UserLoginModel model=new UserLoginModel();
//        Profile user=profilesDao.findProfileByUserNameAndPassword(loginModel.getUserName(), loginModel.getPassword());
//        if (user !=null) {
//            model.setEmail(user.getEmail());
//            model.setFirstName(user.getFirstName());
//            model.setId(user.getId());
//            model.setLastName(user.getLastName());
//            model.setMobile(user.getPassword());
//            model.setPassword(user.getPassword());
//            model.setUserName(user.getUserName());
//        }
        return profilesDao.findProfileByUserNameAndPassword("login", "password");
    }
}
