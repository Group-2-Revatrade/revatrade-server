package com.revature.revatrade.shared.validation;

import com.revature.revatrade.model.User;
import com.revature.revatrade.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    UserDao userDao;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("Line 17: " + value);
        User userDB = userDao.findUserByUsername(value);
        if(userDB == null){
            return true;
        }
        return false;
    }
}