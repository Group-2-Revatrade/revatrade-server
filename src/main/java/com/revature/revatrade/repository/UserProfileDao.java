package com.revature.revatrade.repository;

import com.revature.revatrade.model.User;
import com.revature.revatrade.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userProfileDao")
@Transactional
public interface UserProfileDao extends JpaRepository<UserProfile, Integer> {
    UserProfile findUserProfileByUser(User user);
}
