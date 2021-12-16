package com.revature.revatrade.controller;


import com.revature.revatrade.model.OrderDetails;
import com.revature.revatrade.model.Product;
import com.revature.revatrade.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jms.Session;
import java.util.List;

@RestController("cartController")
@RequestMapping("/cart")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class ShoppingCartController {

    ShoppingCartService cartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(path="/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDetails>> findAll(){
        System.out.println("Controller Activated");
        return new ResponseEntity<List<OrderDetails>>(this.cartService.findAll(), HttpStatus.OK);
    }

    @PostMapping(path="/new", consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addItemToCart(@RequestBody OrderDetails orderDetails){
        this.cartService.addItem(orderDetails);
    }

    //TODO
    @GetMapping(path="search", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDetails>> findById(@RequestParam(value = "userId", required = true) Integer userId){
        return new ResponseEntity<List<OrderDetails>>(this.cartService.findAllById(userId), HttpStatus.OK);
    }


    //change quantities of items in the cart, update all items at the same time
    @PostMapping(path="/update", consumes = MediaType.APPLICATION_JSON_VALUE )
    public void updateAll(@RequestBody OrderDetails orderDetails){
        this.cartService.updateCart(orderDetails);
    }


    // Remove Item from Cart //might change to use order_id instead of the entire order details
    @PostMapping(path = "/del", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteItem(@RequestParam(value = "orderId", required = true) Integer orderId){
        this.cartService.deleteItem(orderId);
        System.out.println("Order-id" + orderId);
    }



    //update the cart.







}
