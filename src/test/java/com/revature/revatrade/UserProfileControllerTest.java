package com.revature.revatrade;

import com.revature.revatrade.controller.UserProfileController;
import com.revature.revatrade.model.User;
import com.revature.revatrade.model.UserProfile;
import com.revature.revatrade.repository.UserDao;
import com.revature.revatrade.repository.UserProfileDao;
import com.revature.revatrade.service.UserProfileService;
import com.revature.revatrade.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.revature.revatrade.TestUser.createValidUser;
import static org.assertj.core.api.Assertions.assertThat;

public class UserProfileControllerTest {

     public static final String API_USERS_USERNAME = "/api/users/{username}";
     public static final String API_USERS_USERNAME_PROFILE = "/api/users/{username}/profile";

     @Autowired
     TestRestTemplate testRestTemplate;

     @Autowired
     UserService userService;

     @Autowired
     UserProfileService profileService;

     @Autowired
     UserDao userDao;


     @Autowired
     UserProfileDao profileDao;

     @BeforeEach
     public void setup(){
          userDao.save(createValidUser());
     }


     @Test
     public void shouldCreateUserProfile(){

          UserProfileController controller = new UserProfileController();

          String username = "username";

          controller.createUserProfile(TestProfile.createValidProfile(), username);
//          Assertions.assertFalse(controller.getUserProfileById(username, 1).equals(null));

          Assertions.assertEquals(1, profileDao.findAll());

     }

     @AfterEach
     public void cleanup(){
         userDao.deleteAll();
         profileDao.deleteAll();
     }
}
