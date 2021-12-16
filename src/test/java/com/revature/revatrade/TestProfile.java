package com.revature.revatrade;

import com.revature.revatrade.model.User;
import com.revature.revatrade.model.UserProfile;

public class TestProfile {
    public static UserProfile createValidProfile() {
        UserProfile profile = new UserProfile();
        profile.setFirstName("firstName");
        profile.setLastName("lastName");
        profile.setCity("NY");
        profile.setAddress("43 Ave 70 street, 3Floor");
        profile.setZipCode(11373);
        profile.setProfilePic("profile_picture.png");
        profile.setAboutMe("This is where you describe about your self so that other can vew and know you");
        profile.setUser( new User());
        return profile;
    }
}
