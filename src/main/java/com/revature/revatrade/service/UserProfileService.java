package com.revature.revatrade.service;

import com.revature.revatrade.model.UserProfile;
import com.revature.revatrade.repository.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {


    UserProfileDao profileDao;

    @Autowired
    public UserProfileService(UserProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public UserProfile persistProfile(UserProfile profile){
        return profileDao.save(profile);
    }

    public UserProfile getProfileById(Integer profileId) {
        if(profileDao.findById(profileId).isPresent()){
            return profileDao.findById(profileId).get();

        }else {
            return null;
        }

    }
//    public UserProfile getProfileByUserId(Integer userId) {
//        return profileDao.findById(userId).get();
//    }

    public UserProfile updateProfile(UserProfile profile) {
        return profileDao.saveAndFlush(profile);
    }
}