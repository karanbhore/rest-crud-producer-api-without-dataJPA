package com.prowings.service;

import java.util.List;

import com.prowings.entity.Product;

public interface ProductService {
	
	public List<Product> getAllProducts();

	public boolean createProduct(Product product);

	public boolean updateProduct(Product product, int id);

	public boolean deleteProduct(int id);

}
