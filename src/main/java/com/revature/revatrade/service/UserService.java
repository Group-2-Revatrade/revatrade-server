package com.revature.revatrade.service;

import com.revature.revatrade.model.User;
import com.revature.revatrade.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
	UserDao userDao;

	@Autowired
	public UserService(UserDao userDao) {
			this.userDao = userDao;
	}

	public User saveUser(User user) {
		return this.userDao.saveUser(user);
	}
}
