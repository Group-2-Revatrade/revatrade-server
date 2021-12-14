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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static com.revature.revatrade.TestUser.createValidUser;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserProfileControllerTest {

<<<<<<< HEAD
     public static final String API_USERS_USERNAME = "/api/users/{username}";
     public static final String API_USERS_USERNAME_PROFILE = "/api/users/{username}/profile";
=======
    public static final String API_USERS_USERNAME_PROFILE = "/api/users/";
>>>>>>> 1a4f2cb

     @Autowired
     TestRestTemplate testRestTemplate;

     @Autowired
     UserService userService;

     @Autowired
     UserProfileService profileService;

     @Autowired
     UserDao userDao;
<<<<<<< HEAD


     @Autowired
     UserProfileDao profileDao;

     @BeforeEach
     public void setup(){
          userDao.save(createValidUser());
     }
=======
     
     @Autowired
     UserProfileDao profileDao;

    @BeforeEach
    public void cleanup(){
         profileDao.deleteAll();
         userService.saveUser(TestUser.createValidUser());
     		
    }
>>>>>>> 1a4f2cb


     @Test
     public void shouldCreateUserProfile(){

          UserProfileController controller = new UserProfileController();

          String username = "username";
<<<<<<< HEAD

          controller.createUserProfile(TestProfile.createValidProfile(), username);
//          Assertions.assertFalse(controller.getUserProfileById(username, 1).equals(null));
=======
          UserProfile profile = TestProfile.createValidProfile();
          ResponseEntity<Object> response = testRestTemplate.postForEntity(API_USERS_USERNAME_PROFILE +username+ "/profile/create", profile, Object.class );
          assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
     }
     
     @Test
     public void getUserProfile_recieveOK() {
    	 String username = "username";
    	 UserProfile profile = TestProfile.createValidProfile();
    	 profile.setUser(TestUser.createValidUser());
    	 profileDao.save(profile);
         ResponseEntity<Object> response = testRestTemplate.getForEntity(API_USERS_USERNAME_PROFILE +username+ "/profile/" + profile.getUserProfileId(), Object.class, profile );
         assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
     }
>>>>>>> 1a4f2cb

          Assertions.assertEquals(1, profileDao.findAll());

     }

<<<<<<< HEAD
     @AfterEach
     public void cleanup(){
         userDao.deleteAll();
         profileDao.deleteAll();
     }
=======
    public <T> ResponseEntity<T> getUser(String username, Class<T> responseType){
         String url = API_USERS_USERNAME_PROFILE;
         return testRestTemplate.getForEntity(url, responseType);
    }
>>>>>>> 1a4f2cb
}
