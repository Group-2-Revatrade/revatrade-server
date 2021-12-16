package com.revature.revatrade.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.revatrade.model.User;
import com.revature.revatrade.service.UserService;
import com.revature.revatrade.service.JwtService;
import com.revature.revatrade.model.JsonResponse;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/jwt")
@CrossOrigin(origins="*")
public class JwtController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
		public JsonResponse userLogin(@RequestBody User user) {
		JsonResponse response;
		user = this.userService.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		if(user != null) {
			String jwt = JwtService.createJWT(UUID.randomUUID().toString(), "Revatrade", String.valueOf(user.getUserId()), 6000000L);
			response = new JsonResponse(true, jwt, user.getUserId());
		} else {
			response = new JsonResponse(false, "Invalid Username and/or Password", null);
		}
		return response;
	}

	@GetMapping("/authenticate")
	public Boolean isLoggedIn(@RequestHeader("Authorization") String jwt) {
		try {
			Claims claim = JwtService.decodeJWT(jwt);
			if(claim.getIssuer().equals("Revatrade")) {
				return true;
			} else {
				return false;
			}
		}catch(io.jsonwebtoken.ExpiredJwtException e) {
			return false;
		}
	}
}
