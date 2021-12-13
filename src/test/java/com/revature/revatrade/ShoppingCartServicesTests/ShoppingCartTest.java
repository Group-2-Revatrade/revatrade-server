package com.revature.revatrade.ShoppingCartTests;

import com.revature.revatrade.service.ShoppingCartService;
import org.junit.Assert;
import org.junit.Test;

public class cartTest {

    ShoppingCartService cartService;
    public cartTest(ShoppingCartService cartService) {
        this.cartService = cartService;
    }



    @Test
    public void DisplayCartItems(){
        Assert.assertEquals(5, this.cartService.findAll().size());
    }
}
