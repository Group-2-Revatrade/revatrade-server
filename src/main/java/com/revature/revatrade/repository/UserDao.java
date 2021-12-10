package com.revature.revatrade.repository;

import com.revature.revatrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDao")
@Transactional
public interface UserDao extends JpaRepository<User, Integer> {
	User findUserByUsername(String username);
	User findUserByUsernameAndPassword(String username, String password);
	User findUserByEmail(String email);
<<<<<<< HEAD
=======

	User saveUser(User user);
>>>>>>> 5f941d27a545ad3402306fc0e1da2eeb6a655437
}
