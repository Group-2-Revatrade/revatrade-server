package com.revature.revatrade.controller;


import com.revature.revatrade.model.OrderDetails;
import com.revature.revatrade.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("cartController")
@RequestMapping("/cart")
public class ShoppingCartController {

    ShoppingCartService cartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(path="/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDetails>> findAll(){
        return new ResponseEntity<List<OrderDetails>>(this.cartService.findAll(), HttpStatus.OK);
    }


    @PostMapping(path="/new", consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addItemToCart(@RequestBody OrderDetails orderDetails){
        this.cartService.addItem(orderDetails);
    }




    //TODO
    //change quantities of items in the cart, update all items at the same time
    @PostMapping(path="/update", consumes = MediaType.APPLICATION_JSON_VALUE )
    public void updateAll(@RequestBody OrderDetails orderDetails){
        this.cartService.updateCart(orderDetails);
    }

    //Remove Item from Cart
    @PostMapping(path = "/del", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteItem(@RequestBody OrderDetails orderDetails){
        this.cartService.deleteItem(orderDetails);
    }



    //update the cart.







}
