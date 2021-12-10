package com.revature.revatrade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.revatrade.model.Product;
import com.revature.revatrade.service.ProductService;

@RestController("productController")
@RequestMapping("/products")
public class ProductController {
	
	ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	
	@GetMapping(path="/all", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> findAll(){
		return new ResponseEntity<List<Product>>(this.productService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(path="/new", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Product saveProduct(@RequestBody Product products) {
		return this.productService.save(products);
		
	}
	
	@GetMapping(path="search", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> searchFor(@RequestParam(value = "term", required = true) String term){
		return new ResponseEntity<List<Product>>(this.productService.searchFor(term), HttpStatus.OK);
	}

}
