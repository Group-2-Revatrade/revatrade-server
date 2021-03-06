package com.revature.revatrade.service;

import com.revature.revatrade.model.User;
import com.revature.revatrade.model.UserProfile;
import com.revature.revatrade.repository.UserDao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
  UserDao userDao;
  BCryptPasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserDao userDao) {
      this.userDao = userDao;
      this.passwordEncoder = new BCryptPasswordEncoder();
  }

  public User saveUser(User user){
      // user.setPassword(passwordEncoder.encode(user.getPassword()));
      return userDao.save(user);
  }

  public User searchUserByUsername(String username){
      User inDB = userDao.findUserByUsername(username);
      
      return inDB;
  }


    public User searchUserByUserId(Integer id){
        User inDB = userDao.findUserByUserId(id);
        return inDB;
    }

    public Integer searchUserIdByUsername(String username){
        User inDB = userDao.findUserByUsername(username);
        return inDB.getProfileId();
    }

    public Integer searchProfileIdByUsername(String username){
        Integer profileId = userDao.findUserByUsername(username).getProfileId();
        return profileId;
    }

    public User updateUser(User user){
      return userDao.saveAndFlush(user);
    }

  public User findUserByUsernameAndPassword(String username, String password) {
    User user = this.userDao.findUserByUsernameAndPassword(username, password);
    if (user == null) {
      return null; 
    } else {
      return user;
    }
  }
}
