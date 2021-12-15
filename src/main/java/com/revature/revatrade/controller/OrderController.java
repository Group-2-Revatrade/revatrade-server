package com.revature.revatrade.controller;

import com.revature.revatrade.model.JsonResponse;
import com.revature.revatrade.service.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.revature.revatrade.model.Order;
import com.revature.revatrade.service.OrderService;

@RestController("orderController")
@RequestMapping("/order")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class OrderController {
	OrderService orderService;
    JwtService jwtService;
    
	@Autowired
	OrderController(OrderService orderService, JwtService jwtService)
	{
		this.orderService = orderService;
		this.jwtService = jwtService;
	}

	@PostMapping(path="/new")
	public JsonResponse saveOrder(@RequestHeader("Authorization") String jwt, @RequestBody Order order)
	{
		JsonResponse response;
		// Get JWT token
		Claims claim = jwtService.decodeJWT(jwt);
		// Validating the token is authentic to our application
		if(claim.getIssuer().equals("Revatrade")) {
			// Get the user id from the jwt token
			Integer userId = Integer.parseInt(claim.getSubject());
			// Attempting to save order
			Order savedOrder = this.orderService.save(order, userId);
			if(savedOrder != null) {
				response = new JsonResponse(true, "Order successfully placed", savedOrder);
			} else {
				response = new JsonResponse(false, "An error occurred, order was not placed", null);
			}
		} else {
			response = new JsonResponse(false, "Invalid token", null);
		}
		return response;
	}
}
