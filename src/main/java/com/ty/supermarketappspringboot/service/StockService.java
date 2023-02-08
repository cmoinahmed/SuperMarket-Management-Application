package com.ty.supermarketappspringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.supermarketappspringboot.dao.StockDao;
import com.ty.supermarketappspringboot.dao.SuperMarketDao;
import com.ty.supermarketappspringboot.dto.ResponseStructure;
import com.ty.supermarketappspringboot.dto.Stock;
import com.ty.supermarketappspringboot.dto.SuperMarket;
import com.ty.supermarketappspringboot.exception.DataNotFoundException;
import com.ty.supermarketappspringboot.exception.IdNotFoundException;
import com.ty.supermarketappspringboot.exception.InvalidStockTypeException;

@Service
public class StockService {
	@Autowired
	private StockDao dao;

	@Autowired
	private SuperMarketDao superMarketDao;

	public ResponseEntity<ResponseStructure<Stock>> registerStock(String supermarketId, Stock stock) {
		ResponseStructure<Stock> responseStructure = new ResponseStructure<Stock>();

		SuperMarket market = superMarketDao.getSuperMarket(supermarketId);
		if (market != null) {
			stock.setSuperMarket(market);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Stock Is CREATED");
			Stock stock1 = dao.registerStock(stock);
			responseStructure.setData(stock1);
			return new ResponseEntity<ResponseStructure<Stock>>(responseStructure, HttpStatus.CREATED);
		} else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Stock Is Not created");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<Stock>>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Stock>> getStockById(String id) {
		ResponseStructure<Stock> rs = new ResponseStructure<Stock>();
		Stock stock = dao.getStockById(id);
		if (stock != null) {
			rs.setStatus(HttpStatus.OK.value());
			rs.setMessage("Stock Fetched By Id");
			rs.setData(stock);
			return new ResponseEntity<ResponseStructure<Stock>>(rs, HttpStatus.OK);
		} else {
			String msg = "ID " + id + " Not Found";
			throw new IdNotFoundException(msg);
		}
	}

	public ResponseEntity<ResponseStructure<List<Stock>>> getAllStock() {
		ResponseStructure<List<Stock>> responseStructure = new ResponseStructure<>();
		List<Stock> list = dao.getAllStocks();
		if (list.size() > 0) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Got All The Stock");
			responseStructure.setData(dao.getAllStocks());
			return new ResponseEntity<ResponseStructure<List<Stock>>>(responseStructure, HttpStatus.OK);
		} else {
			throw new DataNotFoundException("Stock is Empty");
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteStock(String id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		Stock stock = dao.getStockById(id);
		if (stock != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Stock Deleted Successfully");
			responseStructure.setData(dao.deleteStock(stock));
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
		} else {
			String msg = "ID " + id + " Not Found";
			throw new IdNotFoundException(msg);
		}
	}

	public ResponseEntity<ResponseStructure<Stock>> updateStockById(String id, Stock stock1) {
		ResponseStructure<Stock> rs = new ResponseStructure<Stock>();
		SuperMarket superMarket = superMarketDao.getSuperMarket(id);
		if (superMarket != null) {
			stock1.setSuperMarket(superMarket);
			rs.setStatus(HttpStatus.OK.value());
			rs.setMessage("Stock Updated Successfully");
			rs.setData(dao.registerStock(stock1));
			return new ResponseEntity<ResponseStructure<Stock>>(rs, HttpStatus.OK);
		} else {
			String msg = "ID " + id + " Not Found";
			throw new IdNotFoundException(msg);
		}
	}

	public ResponseEntity<ResponseStructure<List<Stock>>> findByType(String type) {
		ResponseStructure<List<Stock>> responseStructure = new ResponseStructure<>();
		List<Stock> stock = dao.findByType(type);
		if (stock.size() > 0) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Stock Type Successfully Fetched");
			responseStructure.setData(stock);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new InvalidStockTypeException("Stock Type Is Not Available");
		}
	}

}
