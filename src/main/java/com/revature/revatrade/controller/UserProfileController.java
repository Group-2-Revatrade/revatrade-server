package com.revature.revatrade.controller;

import com.revature.revatrade.model.JsonResponse;
import com.revature.revatrade.model.User;
import com.revature.revatrade.model.UserProfile;
import com.revature.revatrade.repository.UserDao;
import com.revature.revatrade.service.UserProfileService;
import com.revature.revatrade.service.UserService;
import com.revature.revatrade.shared.GenericInvalidMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.ws.RespectBinding;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users/")
public class UserProfileController {

    @Autowired
    UserProfileService profileService;

    @Autowired
    UserService userService;

    @PostMapping("{username}/profile/create")
    public JsonResponse createUserProfile(@Valid @RequestBody UserProfile profile, @PathVariable("username") String username){
        JsonResponse response;

        User user = userService.searchByUsername(username);

        if(user != null){
            try{
                profile.setUser(user);
                UserProfile temp = profileService.persistProfile(profile);
                if(temp != null) {
                    temp.setUser(null);
                    response = new JsonResponse(true, "Profile saved successfully", temp);
                } else {
                    response = new JsonResponse(false, "Profile was not able created", null);
                }
                return response;

            }catch (Exception e){
                return  new JsonResponse(false, "Profile was not able created", null);
            }


        }
        else{
            return new JsonResponse(false, " Username Error  ", null);
        }
    }

    @GetMapping("/{username}/profile/{id}")
    public UserProfile getUserProfileById(@PathVariable("id") Integer profileId){
        UserProfile profile = profileService.getProfileById(profileId);
        profile.getUser().setPassword(null);
        return profile;
    }

    @PostMapping("/{username}/profile/{id}")
    public JsonResponse updateUserProfile(@Valid @RequestBody UserProfile profile, @PathVariable("username") String username, @PathVariable("id") Integer profileId){
        UserProfile inDBProfile = profileService.getProfileById(profileId);

        if(inDBProfile != null){
            if(profile.getFirstName() != null) {
                inDBProfile.setFirstName(profile.getFirstName());
            }
            if(profile.getLastName() != null) {
                inDBProfile.setLastName(profile.getLastName());
            }
            if(profile.getProfilePic() != null) {
                inDBProfile.setProfilePic(profile.getProfilePic());
            }
            if(profile.getCity() != null) {
                inDBProfile.setCity(profile.getCity());
            }
            if(profile.getZipCode() != null) {
                inDBProfile.setZipCode(profile.getZipCode());
            }
            if(profile.getAddress() != null) {
                inDBProfile.setAddress(profile.getAddress());
            }
            if(profile.getAboutMe() != null) {
                inDBProfile.setAboutMe(profile.getAboutMe());
            }

            UserProfile temp = profileService.updateProfile(inDBProfile);

            if(temp != null) {
                temp.setUser(null);
                return  new JsonResponse(true, "Profile updated successfully", temp);
            }
            return  new JsonResponse(true, "Profile updating error", profile);
        }
        else {
            return new JsonResponse(false, "Profile not found", null);
        }

    }
}
