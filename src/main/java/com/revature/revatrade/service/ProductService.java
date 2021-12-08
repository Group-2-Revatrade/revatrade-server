package com.revature.revatrade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.revatrade.model.Product;
import com.revature.revatrade.repository.ProductDao;

@Service("producService")
public class ProductService {

	ProductDao productDao;
	
	@Autowired
	public ProductService(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	public List<Product> findAll(){
		return this.productDao.findAll();
	}
	
	public void save(Product products) {
		this.productDao.save(products);
	}
	
	public List<Product> searchFor(String term) {
		return this.productDao.findByproductNameContaining(term);
	}
}
