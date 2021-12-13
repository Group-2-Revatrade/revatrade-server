package com.revature.revatrade.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.revatrade.model.Order;
import com.revature.revatrade.model.User;
import com.revature.revatrade.service.OrderService;
import com.revature.revatrade.service.UserService;

@Controller("orderController")
@RequestMapping("/order")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class OrderController {
	OrderService orderService;
	UserService userService;
	
	@Autowired
	OrderController(OrderService orderService, UserService userService)
	{
		this.orderService = orderService;
		this.userService = userService;
	}
	
	
	@PostMapping(path="/new", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveOrder(@RequestBody HashMap<String, Object> hashObject)
	{
		HashMap<String, Object> result =  new HashMap<String, Object>();
		
		// NOTES(): 
		// Get JWT token
		// Get the user id from the jwt token 
		// Get user profile by User id using userService?
		// Set order to profile id
		try {
			// NOTES(): This is temporary until I get the userId from the JWT token
			int userId = 0;
			////////////////
			
			Order newOrder = new Order();
			//int userId = (int) hashObject.get("userId");
			//User user = userService.findUserByUserId(userId); 
			
			//System.out.println("" + hashObject.toString());
			
			
			newOrder.setAddress( (String) hashObject.get("Address"));
			newOrder.setCity( (String) hashObject.get("City"));
		
			newOrder.setOrderAmount( (double)  hashObject.get("OrderAmount"));
			newOrder.setOrderDate((double) hashObject.get("OrderDate"));
			newOrder.setZipCode((int) hashObject.get("Zipcode"));
			
			orderService.save(newOrder);
			result.put("success", "success");
		}
		catch(Exception e)
		{
			result.put("Error","Could not save order");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
		
	}
}
