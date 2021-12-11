package com.revature.revatrade;

import com.revature.revatrade.model.UserProfile;
import com.revature.revatrade.repository.UserDao;
import com.revature.revatrade.repository.UserProfileDao;
import com.revature.revatrade.service.UserProfileService;
import com.revature.revatrade.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class UserProfileControllerTest {

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

    // @BeforeEach
     public void cleanup(){
          profileDao.deleteAll();
          userService.saveUser(TestUser.createValidUser());
     }


    // @Test
     public void postUserProfile_whenUsernameExist_receiveOK(){
          String username = "username";
          UserProfile profile = TestProfile.createValidProfile();

          ResponseEntity<Object> response = testRestTemplate.postForEntity(API_USERS_USERNAME_PROFILE+"/create", profile, Object.class );
          assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
     }



//     public <T> ResponseEntity<T> getUser(String username, Class<T> responseType){
//          String url = API_USERS_USERNAME;
//          return testRestTemplate.getForEntity(url, responseType);
//     }
}
