package com.revature.revatrade.controller;

import com.revature.revatrade.model.JsonResponse;
import com.revature.revatrade.model.Order;
import com.revature.revatrade.model.OrderDetails;
import com.revature.revatrade.service.JwtService;
import com.revature.revatrade.service.OrderDetailsService;
import com.revature.revatrade.service.OrderService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("orderDetailsController")
@RequestMapping("/api")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class OrderDetailsController {
	OrderDetailsService orderDetailsService;
    JwtService jwtService;

	@Autowired
    OrderDetailsController(OrderDetailsService orderDetailsService, JwtService jwtService)
	{
		this.orderDetailsService = orderDetailsService;
		this.jwtService = jwtService;
	}

	@PostMapping(path="/order/{orderId}/product/{productId}")
	public JsonResponse saveOrderDetails(@RequestHeader("Authorization") String jwt,
										 @PathVariable Integer orderId,
										 @PathVariable Integer productId,
										 @RequestBody OrderDetails orderDetails) {
		JsonResponse response;
		// Get JWT token
		Claims claim = jwtService.decodeJWT(jwt);
		System.out.println(claim);
		System.out.println(claim.getSubject());
		if(claim == null) {
			return new JsonResponse(false, "Expired token", null);
		}
		// Validating the token is authentic to our application
		else if(claim.getIssuer().equals("Revatrade")) {
			// Get the user id from the jwt token
			Integer userId = Integer.parseInt(claim.getSubject());
			// Attempting to save order
			OrderDetails savedDetails = this.orderDetailsService.save(orderDetails,orderId, productId);
			if(savedDetails != null) {
				response = new JsonResponse(true, "Order's detail successfully saved", null);
			} else {
				response = new JsonResponse(false, "An error occurred", null);
			}
		}
		else {
			response = new JsonResponse(false, "Invalid token", null);
		}
		return response;
	}
}
