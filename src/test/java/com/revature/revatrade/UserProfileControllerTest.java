package com.revature.revatrade;

import com.revature.revatrade.controller.ProductController;
import com.revature.revatrade.controller.UserProfileController;
import com.revature.revatrade.model.Product;
import com.revature.revatrade.model.UserProfile;
import com.revature.revatrade.service.ProductService;
import com.revature.revatrade.service.UserProfileService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserProfileControllerTest {
    public static final String API_USERS_USERNAME = "/api/users/username";
    @Mock
    private UserProfileService profileService;

    private MockMvc mockmvc;

    @InjectMocks
    private UserProfileController profileController;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockmvc = MockMvcBuilders.standaloneSetup(profileController).build();
    }

    @Test
    public void testSave() throws Exception {
        UserProfile profile = TestProfile.createValidProfile();
        profile.setUser(TestUser.createValidUser());
        Mockito.when(profileService.persistProfile(profile)).thenReturn(profile);

        this.mockmvc.perform(post(API_USERS_USERNAME + "/create").contentType(MediaType.APPLICATION_JSON));

    }
}
