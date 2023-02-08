package com.ty.supermarketappspringboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.supermarketappspringboot.dto.Product;
import com.ty.supermarketappspringboot.repository.ProductRepository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository productRepository;

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public Product getProduct(String id) {
		return productRepository.findById(id);
	}

	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	public Product deleteProduct(Product product) {
		
		productRepository.delete(product);
		return product;
	}

	public List<Product> getProductByBrand(String brand) {
		return productRepository.findByBrand(brand);
	}

	public List<Product> getProductByTypeOfProduct(String typeOfProduct) {
		return productRepository.findByTypeOfProduct(typeOfProduct);
	}

	public List<Product> getProductByPriceRange(double startPrice, double endPrice) {
		return productRepository.findProductByPriceRange(startPrice, endPrice);
	}
}
