package com.revature.revatrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.revatrade.model.Product;

@Repository("productDao")
public interface ProductDao extends JpaRepository <Product, Integer> {
	
	public List<Product> findAll();
	

}
