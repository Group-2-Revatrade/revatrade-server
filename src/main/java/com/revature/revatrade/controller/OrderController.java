package com.revature.revatrade.controller;

import com.revature.revatrade.model.JsonResponse;
import com.revature.revatrade.service.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.revature.revatrade.model.Order;
import com.revature.revatrade.service.OrderService;
import com.revature.revatrade.service.JwtService;

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
	public JsonResponse saveOrder(@RequestBody Order order, @RequestHeader("Authorization") String jwt)
	{
		JsonResponse response;
    try {
      // Get JWT token
      int userId = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
      // Attempting to save order
      Order savedOrder = this.orderService.save(order, userId);
      if(savedOrder != null) {
        response = new JsonResponse(true, "Order successfully placed", savedOrder);
      } else {
        response = new JsonResponse(false, "An error occurred, order was not placed", null);
      }
    }catch(io.jsonwebtoken.MalformedJwtException e) {
      response = new JsonResponse(false, "Invalid token", null);
    }
		return response;
	}
}
