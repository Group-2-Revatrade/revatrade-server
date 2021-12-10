package com.revature.revatrade.service;

import com.revature.revatrade.model.User;
import com.revature.revatrade.repository.UserDao;
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
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      return userDao.save(user);
  }
<<<<<<< HEAD
=======

  /**
	public User saveUser(User user) {
		return this.userDao.saveUser(user);
	}
   ***/
>>>>>>> 5f941d27a545ad3402306fc0e1da2eeb6a655437
}
