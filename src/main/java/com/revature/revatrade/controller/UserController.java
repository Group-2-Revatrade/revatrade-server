package com.revature.revatrade.controller;

import com.revature.revatrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
