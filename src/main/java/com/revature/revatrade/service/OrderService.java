package com.revature.revatrade.service;

import com.revature.revatrade.model.User;
import com.revature.revatrade.model.UserProfile;
import com.revature.revatrade.repository.UserDao;
import com.revature.revatrade.repository.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.revatrade.model.Order;
import com.revature.revatrade.repository.OrderDao;

import java.util.Calendar;

@Service("orderService")
public class OrderService {
	OrderDao orderDao;
	UserDao userDao;
	UserProfileDao userProfileDao;
	
	@Autowired
	OrderService(OrderDao orderDao, UserDao userDao, UserProfileDao userProfileDao)
	{
		this.orderDao = orderDao;
		this.userDao = userDao;
		this.userProfileDao = userProfileDao;
	}

	public Order save(Order order, Integer userId)
	{
		// Retrieving user
		User user = this.userDao.findById(userId).orElse(null);
		if(user != null) {
			// Retrieving user profile
			UserProfile userProfile = this.userProfileDao.findUserProfileByUser(user);
			if(userProfile != null) {
				order.setUserProfile(userProfile);
				order.setOrderDate(Calendar.getInstance());
				// Saving order...
				return orderDao.save(order);
			}
		}
		return null;
	}
}
