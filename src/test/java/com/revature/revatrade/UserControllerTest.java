package com.revature.revatrade;

import com.revature.revatrade.model.JsonResponse;
import com.revature.revatrade.model.User;
import com.revature.revatrade.repository.UserDao;
import com.revature.revatrade.shared.GenericInvalidMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

    public static final String API_USERS = "/api/users";

    @Autowired //Field injection
    TestRestTemplate testRestTemplate; //To test Api end point (applicationContext)

    @Autowired
    UserDao userDao;

    @BeforeEach
    public void cleanup(){
        userDao.deleteAll();
    }

    @Test
    public void postUser_whenUserIsValid_receiveOk(){
        User user = getUser();

        ResponseEntity<Object> response = postSignup(user, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void postUser_whenUserIsValid_userSavedToDatabase(){
        User user = getUser();

        postSignup(user, Object.class);
        assertThat(userDao.count()).isEqualTo(1);
    }

    @Test
    public void postUser_whenUserIsValid_receiveSuccessMessage(){
        User user = getUser();

        ResponseEntity<JsonResponse> response = postSignup(user, JsonResponse.class);
        assertThat(response.getBody().getMessage()).isNotNull();
    }

    @Test
    public void postUser_whenUserIsValid_passwordIsHashedInDatabase(){

        User user = getUser();
        postSignup(user, Object.class);

        List<User> users = userDao.findAll();
        User inDB = users.get(0);
        assertThat(inDB.getPassword()).isNotEqualTo(user.getPassword());
    }

    @Test
    public void  postUser_whenUserHasNullUsername_receiveBadRequest(){
        User user = getUser();
        user.setUsername(null);
        ResponseEntity<Object> response = postSignup(user, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void  postUser_whenUserHasNullEmail_receiveBadRequest(){
        User user = getUser();
        user.setEmail(null);
        ResponseEntity<Object> response = postSignup(user, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void  postUser_whenUserHasNullPassword_receiveBadRequest(){
        User user = getUser();
        user.setPassword(null);
        ResponseEntity<Object> response = postSignup(user, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @Disabled
    public void  postUser_whenUserHasNullUserType_receiveBadRequest(){
        User user = getUser();
        user.setUserType(null);
        ResponseEntity<Object> response = postSignup(user, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void  postUser_whenUserHasPasswordWithLessThanRequired_receiveBadRequest(){
        User user = getUser();
        user.setPassword("p4ss");
        ResponseEntity<Object> response = postSignup(user, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUser_whenUserHasNullUsername_receiveMessageOfUsernameIsRequired(){
        User user = getUser();
        user.setUsername(null);
        ResponseEntity<GenericInvalidMessage> response = postSignup(user, GenericInvalidMessage.class);
        Map<String, String> validationErrors = response.getBody().getValidationErrors();
        assertThat(validationErrors.get("username")).isEqualTo("Username is required");
    }

    @Test
    public void postUser_whenUserHasNullEmail_receiveMessageOfEmailIsRequired(){
        User user = getUser();
        user.setEmail(null);
        ResponseEntity<GenericInvalidMessage> response = postSignup(user, GenericInvalidMessage.class);
        Map<String, String> validationErrors = response.getBody().getValidationErrors();
        assertThat(validationErrors.get("email")).isEqualTo("Email is required");
    }

    @Test
    public void postUser_whenUserHasNullPassword_receiveMessageOfPasswordIsRequired(){
        User user = getUser();
        user.setPassword(null);
        ResponseEntity<GenericInvalidMessage> response = postSignup(user, GenericInvalidMessage.class);
        Map<String, String> validationErrors = response.getBody().getValidationErrors();
        assertThat(validationErrors.get("password")).isEqualTo("Password is required");
    }

    @Test
    public void postUser_whenUserHasPasswordWithLessThanRequired__receiveMessageOfSizeError(){
        User user = getUser();
        user.setPassword("P4ss");
        ResponseEntity<GenericInvalidMessage> response = postSignup(user, GenericInvalidMessage.class);
        Map<String, String> validationErrors = response.getBody().getValidationErrors();
        assertThat(validationErrors.get("password")).isEqualTo("Password must be minimum 5 characters long");
    }

    @Test
    public void postUser_whenAnotherUserHasSameUsername_receiveBadRequest(){
        userDao.save(getUser());
        User user = getUser();
        ResponseEntity<Object> response = postSignup(user, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    public void postUser_whenAnotherUserHasSameUsername_receiveMessageOfDuplicateUsername(){
        userDao.save(getUser());

        User user = getUser();
        ResponseEntity<GenericInvalidMessage> response = postSignup(user, GenericInvalidMessage.class);
        Map<String, String> validationErrors = response.getBody().getValidationErrors();
        assertThat(validationErrors.get("username")).isEqualTo("Username already taken");
    }

    @Test
    public void postUser_whenUserEnterNotEmail_receiveBadRequest(){
        User user = getUser();
        user.setEmail("user_mail");
        ResponseEntity<Object> response = postSignup(user, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUser_whenUserEnterNotEmail_receiveMessageOfNotEmail(){
        User user = getUser();
        user.setEmail("user_mail");
        ResponseEntity<GenericInvalidMessage> response = postSignup(user, GenericInvalidMessage.class);
        Map<String, String> validationErrors = response.getBody().getValidationErrors();
        assertThat(validationErrors.get("email")).isEqualTo("must be a well-formed email address");
    }

    @Test
    public void postUser_whenAnotherUserHasSameEmail_receiveMessageOfDuplicateEmail(){
        userDao.save(getUser());

        User user = getUser();
        ResponseEntity<GenericInvalidMessage> response = postSignup(user, GenericInvalidMessage.class);
        Map<String, String> validationErrors = response.getBody().getValidationErrors();
        assertThat(validationErrors.get("email")).isEqualTo("Email address already taken");
    }

    public <T> ResponseEntity<T> postSignup(Object request, Class<T> response){
        return testRestTemplate.postForEntity(API_USERS, request, response);
    }

    private User getUser() {
        User user = new User();
        user.setUsername("username");
        user.setEmail("user@mail.com");
        user.setUserType("Customer");
        user.setPassword("p4ssWord");
        return user;
    }

}