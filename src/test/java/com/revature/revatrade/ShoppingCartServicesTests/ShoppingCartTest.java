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

   /** public ShoppingCartTest(ShoppingCartService cartService) {
        this.cartService = cartService;
    }**/

    @Before
    public void setupMockCart(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }



    @Test
    public void AddItemsToCartTest(){
        List<OrderDetails> addToCart = new ArrayList<>();
        addToCart.add(new OrderDetails(1,19.50,2.0,null, null,null));
        addToCart.add(new OrderDetails(2 , 3.50, 1.0 , null, null,null));
        int x = this.cartService.findAll().size(); //number of items in the cart to start.
        int y = addToCart.size(); //number of item added
        this.cartController.addItemToCart(addToCart.iterator().next()); //add items to the cart
        Mockito.when(cartService.findAll()).thenReturn(addToCart);
        System.out.println(x);
        Assert.assertEquals(3, this.cartService.findAll().size()-x , 0);
    }

    @Test
    public void RemoveProductFromCartTest(){
        List<OrderDetails> addToCart = new ArrayList<>();
        addToCart.add(new OrderDetails(1,19.50,2.0,null, null,null));
        addToCart.add(new OrderDetails(2 , 3.50, 1.0 , null, null,null));
        this.cartController.addItemToCart(addToCart.iterator().next());
        System.out.println(cartController.findAll().toString());
        this.cartController.deleteItem(addToCart.get(addToCart.indexOf(addToCart.contains("2.0"))+1));
        Assert.assertFalse(this.cartService.findAll().contains(addToCart.contains("2.0")));
    }




}
/**
 *        @Mock
 *    private ProductService productService;
 *
 * 	private MockMvc mockmvc;
 *
 *    @InjectMocks
 *    private ProductController productController;
 *
 *    @Before
 *    public void setup() {
 * 		MockitoAnnotations.openMocks(this);
 * 		mockmvc = MockMvcBuilders.standaloneSetup(productController).build();
 *    }
 *
 *    @Test
 *    public void testFindAll() throws Exception {
 * //		System.out.println("test test");
 *
 * 		List<Product> products = new ArrayList<>();
 * 		products.add(new Product(1, "dslr camera", 200.0, 50, true, 20.0, "cannan dslr camera", "www.pic.com"));
 * 		products.add(new Product(2, "hp laptop", 1000.0, 10, true, 10.0, "intel i7 with 16gb ram", "www.pic.com"));
 *
 *
 * 		Mockito.when(productService.findAll()).thenReturn(products);
 *
 * 		this.mockmvc.perform(get("/products/all"))
 * 		.andExpect(status().isOk())
 * 		.andExpect(jsonPath("$[0].productId", is(1)));
 *    }
 *
 *    @Test
 *    public void testSave() throws Exception {
 * 		Product prd = new Product(1, "dslr camera", 200.0, 50, true, 20.0, "cannan dslr camera", "www.pic.com");
 * 		Mockito.when(productService.save(prd)).thenReturn(prd);
 *
 * 		this.mockmvc.perform(post("/products/new").contentType(MediaType.APPLICATION_JSON));
 *
 *    }
 *
 *
 */
