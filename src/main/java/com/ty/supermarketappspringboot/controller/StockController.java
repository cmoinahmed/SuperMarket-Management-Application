package com.ty.supermarketappspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.supermarketappspringboot.dto.ResponseStructure;
import com.ty.supermarketappspringboot.dto.Stock;
import com.ty.supermarketappspringboot.service.StockService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/stock")
@CrossOrigin(origins = "*")
public class StockController {
	@Autowired
	private StockService service;

	@ApiOperation(value = "Save Stock", notes = "Input is Stock Object and return Stock object")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED") })
	@PostMapping(value = "/save", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<Stock>> registerStock(@RequestParam String supermarketId,
			@RequestBody Stock stock) {
		return service.registerStock(supermarketId, stock);
	}

	@ApiOperation(value = "get Stock by id", notes = "Input is Stock Id and return Stock object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(value = "/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<Stock>> getStockById(@PathVariable String id) {
		return service.getStockById(id);
	}

	@ApiOperation(value = "Get All Stocks")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@GetMapping(value = "/getall", produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<List<Stock>>> getAllStock() {
		return service.getAllStock();
	}

	@ApiOperation(value = "delete Stock by id", notes = "Input is Stock Id ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@DeleteMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<String>> deleteStock(@PathVariable String id) {
		return service.deleteStock(id);
	}

	@ApiOperation(value = "update Stock by id", notes = "Input is Stock Id, Stock object and return updated Stock object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PutMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE ,MediaType.ALL_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<Stock>> updateStockById(@RequestParam String supermarketId, @RequestBody Stock stock) {
		return service.updateStockById(supermarketId, stock);
	}

	@ApiOperation(value = "Get All Products by type")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@GetMapping(value = "/getproducts/{type}", produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<List<Stock>>> getAllProductsByType(@PathVariable String type) {

		return service.findByType(type);
	}

}
