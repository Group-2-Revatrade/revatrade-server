package com.revature.revatrade;

import com.revature.revatrade.model.User;

public class TestUser {
    public static User createValidUser() {
        User user = new User();
        user.setUsername("username");
        user.setEmail("user@mail.com");
        user.setUserType("Customer");
        user.setPassword("p4ssWord");
        user.setProfileId(null);
        return user;
    }
}
