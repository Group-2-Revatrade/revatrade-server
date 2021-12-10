package com.revature.revatrade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.revature.revatrade.model.Product;
import com.revature.revatrade.repository.ProductDao;
import com.revature.revatrade.service.ProductService;


@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceTest {
	
	MockMvc mockMvc;
	
	@InjectMocks
	ProductService productService;
	
	@Mock
	ProductDao productDao;
	
	
	@BeforeAll
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productService).build();
	}
	
	@Test
	public void testFindAll() {
		System.out.println("test");
		List<Product> products = new ArrayList<>();
		products.add(new Product(1, "dslr camera", 200.0, 50, true, 20.0, "cannan dslr camera", "www.pic.com"));
		products.add(new Product(2, "hp laptop", 1000.0, 10, true, 10.0, "intel i7 with 16gb ram", "www.pic.com"));
		
		Mockito.when(productDao.findAll()).thenReturn(products);
		
		assertEquals(1, products.get(0).getProductId());
			
	}
	
	@Test
	public void TestSave() {
		
		Product prd = new Product(1, "dslr camera", 200.0, 50, true, 20.0, "cannan dslr camera", "www.pic.com");
	
		Mockito.when(productDao.save(prd)).thenReturn(new Product());
		productDao.save(prd);
		
		verify(productDao, times(1)).save(prd);
		
	}

}
