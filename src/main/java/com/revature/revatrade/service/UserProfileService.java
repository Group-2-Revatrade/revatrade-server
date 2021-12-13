package com.revature.revatrade.service;

import com.revature.revatrade.model.User;
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
        return profileDao.findById(profileId).get();
    }

    public UserProfile updateProfile(UserProfile profile) {
        return profileDao.saveAndFlush(profile);
    }
}
