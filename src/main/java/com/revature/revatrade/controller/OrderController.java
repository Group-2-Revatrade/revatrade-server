package com.revature.revatrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.revatrade.model.Order;
import com.revature.revatrade.service.OrderService;

@Controller("orderController")
@RequestMapping("/order")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class OrderController {
	OrderService orderService;
	
	@Autowired
	OrderController(OrderService orderService)
	{
		this.orderService = orderService;
	}


	
	
	@PostMapping(path="/new", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void saveOrder(@RequestBody Order order)
	{
		// NOTES(): 
		// Get JWT token
		// Get the token from the user id
		// Get user profile by User id using userService?
		// Set order to profile id 
		orderService.save(order);
	}
}
