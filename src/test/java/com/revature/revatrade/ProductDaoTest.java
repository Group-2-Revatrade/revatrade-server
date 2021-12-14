package com.revature.revatrade;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.revature.revatrade.model.Product;
import com.revature.revatrade.repository.ProductDao;

@Transactional
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(classes = RevatradeApplication.class)
@ContextConfiguration(classes=Product.class)
public class ProductDaoTest {
	
	@Autowired
	private ProductDao productDao;
	
	@Test
	public void testSave() {
		
		Product prds = new Product();
		prds.setProductName("HP desktop");
		prds.setProductPrice(200.0);
		prds.setProductQuantity(10);
		prds.setDiscount(true);
		prds.setDiscountRate(20.1);
		prds.setDescription("good for officail use");
		prds.setProductPic("/hdf/dfe/dfe");
		
		productDao.save(prds);
		
//		Mockito.when(productDao.save(prds)).thenReturn(new Product());
		
		
	}
	
	@Test
	public void testFindall() {
			
			List<Product> prods = productDao.findAll();
			
//			Mockito.when(productDao.findAll()).thenReturn(prods);
			for(int i = 0; i<prods.size(); i++) {
				
				assertEquals(i, prods.size(), "size should depend witht the db" );
			}
//			
			System.out.println(prods.size()+" nnnnnnnnnn" );
//		
//		List<Product> prods = new ArrayList<>();
//		prods.add(new Product(1, "dslr camera", 200.0, 50, true, 20.0, "cannan dslr camera", "www.pic.com"));
//		prods.add(new Product(2, "hp laptop", 1000.0, 10, true, 10.0, "intel i7 with 16gb ram", "www.pic.com"));
//		
			
//			assertEquals(10.0, prods.get(1).getDiscountRate());
			
			
//			assertEquals(2, prods.size());
	}
	
	@Test
	public void testSearchByname() {
		List<Product> prods = productDao.findByproductNameContaining("name");
		
		for(int i = 0; i<prods.size(); i++) {
			
			assertEquals(i, prods.size());
		}
		
	}


	
}
