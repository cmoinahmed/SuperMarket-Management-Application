package com.ty.supermarketappspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.supermarketappspringboot.dto.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

	Stock findById(String id);


	List<Stock> getByType(String type);

}
