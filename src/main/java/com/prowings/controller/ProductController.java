package com.prowings.controller;

import static com.prowings.util.Constants.ERROR_WHILE_DELETING;
import static com.prowings.util.Constants.ERROR_WHILE_STORING;
import static com.prowings.util.Constants.ERROR_WHILE_UPDATING;
import static com.prowings.util.Constants.SUCCESSFULLY_DELETED;
import static com.prowings.util.Constants.SUCCESSFULLY_STORED;
import static com.prowings.util.Constants.SUCCESSFULLY_UPDATED;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prowings.entity.Product;
import com.prowings.service.ProductService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
//@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts()
	{
		log.info("Request received to fetch all Products");
		List<Product> prodList = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(prodList, HttpStatus.OK);
	}
	
	@PostMapping("/products")
	public ResponseEntity<String> createProduct(@RequestBody Product product)
	{
		log.info("Request received to store the Product : {}", product);
		String res = (productService.createProduct(product)) ?  SUCCESSFULLY_STORED: ERROR_WHILE_STORING;
		return new ResponseEntity<String>(res, HttpStatus.CREATED);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<String> updateProduct(@RequestBody Product product,@PathVariable int id)
	{
		log.info("Request received to update the Product : {}", product);
		String res = (productService.updateProduct(product,id)) ?  SUCCESSFULLY_UPDATED: ERROR_WHILE_UPDATING;
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id)
	{
		log.info("Request received to delete the Product of id: {}", id);
		String res = (productService.deleteProduct(id)) ?  SUCCESSFULLY_DELETED: ERROR_WHILE_DELETING;
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
}
