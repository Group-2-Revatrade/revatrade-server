package com.revature.revatrade.controller;


import com.revature.revatrade.model.OrderDetails;
import com.revature.revatrade.model.Product;
import com.revature.revatrade.service.CartService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("Cartcontroller")
@RequestMapping("/shoppingCart")
public class CartController {

    CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(path="/new", consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addItemToCart(@RequestBody OrderDetails orderDetails){
        cartService.addItem(orderDetails);
    }


    @GetMapping(path="/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDetails>> findAll(){
        return new ResponseEntity<List<OrderDetails>>(cartService.findAll(), HttpStatus.OK);
    }

    //TODO
    //change quantities of items in the cart
    //Remove Item from Cart
    //update the cart.







}
