package com.revature.revatrade.controller;

import com.revature.revatrade.model.JsonResponse;
import com.revature.revatrade.model.User;
import com.revature.revatrade.service.UserService;
import com.revature.revatrade.shared.GenericInvalidMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import com.revature.revatrade.service.JwtService;

@RestController("userController")
@CrossOrigin(origins="*")
@RequestMapping("/api")
//@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class UserController {
  UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/test")
  public String test() {
    System.out.println("GetMapping > test >>> this controller is being reached");
    return "GetMapping >>> this controller is being reached";
  }

  @GetMapping("/testjwt")
  public JsonResponse testJWT(@RequestHeader("Authorization") String jwt){
    JsonResponse response;
    try {
      int id = Integer.valueOf((String)JwtService.decodeJWT(jwt).get("sub"));
      System.out.println("GetMapping > testJWT >>> this JWT controller is being reached -JWT GOOD > ID: " + id);
      response = new JsonResponse(true, "User authenticated & identified successfully", id);
    }catch(java.lang.NullPointerException e) {
      System.out.println("GetMapping > testJWT >>> this JWT controller is being reached -JWT ERROR");
      response = new JsonResponse(false, "User was not successfully authenticated", null);
    }
    return response;
  }

  @PostMapping("/users")
  public JsonResponse createUser(@Valid @RequestBody User user){
    JsonResponse response;
    if(user.getUserType() == null){
      user.setUserType("Customer");
    }
    User temp = userService.saveUser(user);
    if(temp != null) {
      temp.setPassword(null);
      temp.setUserType(null);
      response = new JsonResponse(true, "User saved successfully", temp);
    } else {
      response = new JsonResponse(false, "User was not successfully created", null);
    }
    return response;
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  GenericInvalidMessage handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request){
    GenericInvalidMessage invalidMessage = new GenericInvalidMessage(400, "Validation error", request.getServletPath());

    BindingResult result = exception.getBindingResult();

    Map<String, String> validationErrors = new HashMap<>();

    for(FieldError fieldError: result.getFieldErrors()){
        validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
    }
    invalidMessage.setValidationErrors(validationErrors);

    return  invalidMessage;
  }
}
