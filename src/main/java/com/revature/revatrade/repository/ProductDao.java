package com.revature.revatrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.revatrade.model.Product;
import org.springframework.transaction.annotation.Transactional;

@Repository("productDao")
@Transactional
public interface ProductDao extends JpaRepository <Product, Integer> {
	public List<Product> findAll();
	public <S extends Product> S save(S products);
	public List<Product> findByproductNameContaining(String term);
}
