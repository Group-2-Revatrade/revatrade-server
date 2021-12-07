package com.revature.revatrade.controller;

import com.revature.revatrade.model.User;
import com.revature.revatrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userController")
@RequestMapping(value = "api")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class UserController {
	UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(path="/signup", consumes=MediaType.APPLICATION_JSON_VALUE)
	public User saveUser(@RequestBody User user) {
		try {
			return this.userService.saveUser(user);
		} catch(org.springframework.dao.DataIntegrityViolationException e) {
			return null;
		}
	}
}
