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

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/jwt")
@CrossOrigin(origins="*")
public class JwtController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<String> userLogin(@RequestBody User user) {
		System.out.println("@PostMapping > JwtController >>> user.getUsername()11111:"+user.getUsername());
		System.out.println("@PostMapping > JwtController >>> user.getPassword()11111:"+user.getPassword());
		System.out.println("@PostMapping > JwtController >>> user11111: " + user);
		user = this.userService.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		System.out.println("@PostMapping > JwtController >>> user222222: " + user);
		if(user != null) {
			System.out.println("@PostMapping > JwtController >>> user33333: " + user);
			String jwt = JwtService.createJWT(UUID.randomUUID().toString(), "Revatrade", String.valueOf(user.getUserId()), 6000000L);
			System.out.println("@PostMapping > JwtController >>> jwt > GOOD: " + jwt);

			return new ResponseEntity<String>("{\"jwt\":\"" + jwt + "\"}", HttpStatus.OK);
		}
		System.out.println("@PostMapping > JwtController >>> jwt > ERROR");
		return null;
	}

	@GetMapping("/authenticate")
	public Boolean isLoggedIn(@RequestHeader("Authorization") String jwt) {
		System.out.println("@GetMapping > JwtController >>> isLoggedIn > 111111111");
		try {
			Claims claim = JwtService.decodeJWT(jwt);
			if(claim.getIssuer().equals("Revatrade")) {
				System.out.println("@GetMapping > JwtController >>> isLoggedIn > 2222222");
				return true;
			} else {
				System.out.println("@GetMapping > JwtController >>> isLoggedIn > 333333333");
				return false;
			}
		}catch(io.jsonwebtoken.ExpiredJwtException e) {
			System.out.println("@GetMapping > JwtController >>> isLoggedIn > 44444444");
			return false;
		}
	}
}
