package com.revature.revatrade.shared.validation;

import com.revature.revatrade.model.User;
import com.revature.revatrade.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserDao userDao;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("Line 17: " + value);
        User inDB = userDao.findUserByEmail(value);
        if(inDB == null){
            return  true;
        }
        return false;
    }
}