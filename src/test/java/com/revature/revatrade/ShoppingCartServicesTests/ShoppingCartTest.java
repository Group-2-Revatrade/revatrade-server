package com.revature.revatrade.ShoppingCartServicesTests;

import com.revature.revatrade.controller.ShoppingCartController;
import com.revature.revatrade.model.OrderDetails;
import com.revature.revatrade.service.ShoppingCartService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingCartTest {

    @Mock
    private ShoppingCartService cartService;

    private MockMvc mockMvc;

    @InjectMocks
    private ShoppingCartController cartController;

    /**
     * public ShoppingCartTest(ShoppingCartService cartService) {
     * this.cartService = cartService;
     * }
     **/

    @Before
    public void setupMockCart() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }


    @Test
    public void AddItemsToCartTest() {
        List<OrderDetails> addToCart = new ArrayList<>();
        addToCart.add(new OrderDetails(1, 19.50, 2.0, null, null, null, null, null, false));
        addToCart.add(new OrderDetails(2, 3.50, 1.0, null, null, null, null, null, false));
        int x = this.cartService.findAll().size(); //number of items in the cart to start.
        int y = addToCart.size(); //number of item added
        this.cartController.addItemToCart(addToCart.iterator().next()); //add items to the cart
        Mockito.when(cartService.findAll()).thenReturn(addToCart);
        System.out.println(x);
        Assert.assertEquals(3, this.cartService.findAll().size() - x, 0);
    }

    @Test
    public void RemoveProductFromCartTest() {
        List<OrderDetails> addToCart = new ArrayList<>();
        addToCart.add(new OrderDetails(1, 19.50, 2.0, null, null, null, null, null, false));
        addToCart.add(new OrderDetails(2, 3.50, 1.0, null, null, null, null, null, false));
        this.cartController.addItemToCart(addToCart.iterator().next());
        System.out.println(cartController.findAll().toString());
        //this.cartController.deleteItem(addToCart.get(addToCart.indexOf(addToCart.contains("2.0"))+1));
        Assert.assertFalse(this.cartService.findAll().contains(addToCart.contains("2")));
    }


}
