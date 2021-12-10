package com.revature.revatrade;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.revature.revatrade.controller.ProductController;
import com.revature.revatrade.model.Product;
import com.revature.revatrade.service.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
	
	@Mock
	private ProductService productService;
	
	private MockMvc mockmvc;
	
	@InjectMocks
	private ProductController productController;

	@Before 
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(productController).build();
	}
	
	@Test
	public void testFindAll() throws Exception {
//		System.out.println("test test");
		
		List<Product> products = new ArrayList<>();
		products.add(new Product(1, "dslr camera", 200.0, 50, true, 20.0, "cannan dslr camera", "www.pic.com"));
		products.add(new Product(2, "hp laptop", 1000.0, 10, true, 10.0, "intel i7 with 16gb ram", "www.pic.com"));
		
		
		Mockito.when(productService.findAll()).thenReturn(products);
		
		this.mockmvc.perform(get("/products/all"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].productId", is(1)));
	}

	@Test
	public void testSave() throws Exception {
		Product prd = new Product(1, "dslr camera", 200.0, 50, true, 20.0, "cannan dslr camera", "www.pic.com");
		Mockito.when(productService.save(prd)).thenReturn(prd);
		
		this.mockmvc.perform(post("/products/new").contentType(MediaType.APPLICATION_JSON));
		
	}
	
	
	

}
