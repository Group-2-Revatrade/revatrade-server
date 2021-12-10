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

<<<<<<< HEAD
@RestController("cartController")
=======
@RestController("Cartcontroller")
>>>>>>> 5f941d27a545ad3402306fc0e1da2eeb6a655437
@RequestMapping("/cart")
public class CartController {

    CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

<<<<<<< HEAD
    @GetMapping(path="/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDetails>> findAll(){
        return new ResponseEntity<List<OrderDetails>>(this.cartService.findAll(), HttpStatus.OK);
    }


    @PostMapping(path="/new", consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addItemToCart(@RequestBody OrderDetails orderDetails){
        this.cartService.addItem(orderDetails);
    }



=======
    @PostMapping(path="/new", consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addItemToCart(@RequestBody OrderDetails orderDetails){
        cartService.addItem(orderDetails);
    }


    @GetMapping(path="/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDetails>> findAll(){
        return new ResponseEntity<List<OrderDetails>>(cartService.findAll(), HttpStatus.OK);
    }
>>>>>>> 5f941d27a545ad3402306fc0e1da2eeb6a655437

    //TODO
    //change quantities of items in the cart, update all items at the same time
    @PostMapping(path="/update", consumes = MediaType.APPLICATION_JSON_VALUE )
    public void updateAll(@RequestBody OrderDetails orderDetails){
<<<<<<< HEAD
        this.cartService.updateCart(orderDetails);
=======
        cartService.updateCart(orderDetails);
>>>>>>> 5f941d27a545ad3402306fc0e1da2eeb6a655437
    }

    //Remove Item from Cart
    @PostMapping(path = "/del", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteItem(@RequestBody OrderDetails orderDetails){
<<<<<<< HEAD
        this.cartService.deleteItem(orderDetails);
=======
        cartService.deleteItem(orderDetails);
>>>>>>> 5f941d27a545ad3402306fc0e1da2eeb6a655437
    }



    //update the cart.







}
