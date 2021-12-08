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

	public User saveUser(User user) {
		return this.userDao.saveUser(user);
	}
}