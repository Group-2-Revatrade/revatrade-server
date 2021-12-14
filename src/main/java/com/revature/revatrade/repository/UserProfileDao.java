package com.revature.revatrade.repository;

import com.revature.revatrade.model.User;
import com.revature.revatrade.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserProfileDao extends JpaRepository<UserProfile, Integer> {

////    Optional<UserProfile> findByUser(Integer UserId);
//    @Modifying
//    @Query("from UserProfile p inner join user u where p.user_id = u.user_id")
//    UserProfile findUserProfileByUser(@Param("user") User user);

}
