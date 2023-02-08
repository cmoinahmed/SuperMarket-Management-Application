package com.ty.supermarketappspringboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.supermarketappspringboot.dto.Stock;
import com.ty.supermarketappspringboot.repository.StockRepository;

@Repository
public class StockDao {

	@Autowired
	private StockRepository stockRepository;

	public Stock registerStock(Stock stock) {

		return stockRepository.save(stock);
	}

	public Stock getStockById(String id) {

		return stockRepository.findById(id);
	}

	public List<Stock> getAllStocks() {

		return stockRepository.findAll();
	}

	public String deleteStock(Stock stock) {

		stockRepository.delete(stock);
		return "deleted";
	}

	public List<Stock> findByType(String type) {

		return stockRepository.getByType(type);
	}

}
