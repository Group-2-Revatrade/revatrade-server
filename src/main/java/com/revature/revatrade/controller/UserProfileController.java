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
//import javax.xml.ws.RespectBinding;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class UserProfileController {

    @Autowired
    UserProfileService profileService;

    @Autowired
    UserService userService;

    @GetMapping("/{username}/profile")
    public JsonResponse findUserProfile(@PathVariable("username") String username){

        try{
            User user = userService.searchUserByUsername(username);

            if(user.getProfileId() != null){

                UserProfile profile = profileService.getProfileById(user.getProfileId());
                profile.getUser().setPassword(null);

                return  new JsonResponse(true, " Getting user profile ", profile);
            }else{
                UserProfile userProfile = new UserProfile();
                User inDbUser = userService.searchUserByUsername(username);
                userProfile.setUser(inDbUser);
                userProfile.getUser().setPassword(null);
                return new JsonResponse(true, "Getting user details", userProfile);
            }

        }
        catch (Exception e){
            return new JsonResponse(false, " Username Error  ", null);
        }

    }

    @PostMapping("{username}/profile/create")
    public JsonResponse createUserProfile(@Valid @RequestBody UserProfile profile, @PathVariable("username") String username){
        JsonResponse response;

        User user = userService.searchUserByUsername(username);

        if(user != null){
            try{
                profile.setUser(user);
                UserProfile temp = profileService.persistProfile(profile);
                user.setProfileId(temp.getUserProfileId());
                userService.updateUser(user);
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
    public JsonResponse getUserProfileById(@PathVariable("username") String username, @PathVariable("id") Integer profileId){
        UserProfile userProfile = new UserProfile();
        try{
            UserProfile profile = profileService.getProfileById(profileId);

            if(profile != null){
                profile.getUser().setPassword(null);
                return new JsonResponse(true, "Profile Access successfully ", profile);
            }else{
                return new JsonResponse(false, " Profile not found  ", null);
            }
        }catch (Exception e){
            return new JsonResponse(false, " Data not found  ", null);

        }


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
