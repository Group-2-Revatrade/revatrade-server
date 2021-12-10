package com.revature.revatrade.repository;

import com.revature.revatrade.model.User;
import com.revature.revatrade.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserProfileDao extends JpaRepository<UserProfile, Integer> {

//    @Modifying
//    @Query("from UserProfile p inner join p.user")
//    UserProfile findUserProfileByUser(@Param("user") User user);

}
