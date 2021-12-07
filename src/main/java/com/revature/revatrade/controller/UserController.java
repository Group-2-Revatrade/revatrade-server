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

@RestController("userController")
@RequestMapping("/api")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public JsonResponse createUser(@Valid @RequestBody User user){
        if(user.getUserType() == null){
            user.setUserType("Customer");
        }
        userService.saveUser(user);
//        user.setPassword(null);
//        user.setUsername(null);
//        user.setUserType(null);

        return new JsonResponse(true, "User saved Successfully", user);
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
