package com.ty.supermarketappspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ty.supermarketappspringboot.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public Product findById(String id);

	public List<Product> findByBrand(String brand);

	public List<Product> findByTypeOfProduct(String typeOfProduct);

	@Query(value = "SELECT P FROM Product P WHERE P.priceOfProduct BETWEEN :startPrice AND :endPrice")
	public List<Product> findProductByPriceRange(@Param("startPrice") double startPrice,
			@Param("endPrice") double endPrice);
}
