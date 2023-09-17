package com.prowings.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prowings.dao.ProductRepository;
import com.prowings.entity.Product;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts() {

		return productRepository.getAllProductsFromDb();
	}

	@Override
	public boolean createProduct(Product product) {
		log.info("inside service - createProduct()");
		return productRepository.createProduct(product);
	}

	@Override
	public boolean updateProduct(Product product, int id) {
		log.info("inside service - updateProduct()");
		return productRepository.updateProduct(product,id);
	}

	@Override
	public boolean deleteProduct(int id) {
		log.info("inside service - deleteProduct()");
		return productRepository.deleteProduct(id);
	}

}
