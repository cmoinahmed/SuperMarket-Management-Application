package com.ty.supermarketappspringboot.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.supermarketappspringboot.dto.Bill;
import com.ty.supermarketappspringboot.dto.ResponseStructure;
import com.ty.supermarketappspringboot.service.BillService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/bills")
@CrossOrigin(origins = "*")
public class BillController {

	@Autowired
	private BillService billService;

	@ApiOperation(value = "Save Bill", notes = "Input is bill Object and return billl object")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED") })
	@PostMapping(value = "/save/{customerId}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<Bill>> saveBill(@PathVariable String customerId, @RequestBody Bill bill) {

		return billService.saveBill(customerId, bill);
	}

	@ApiOperation(value = "get Bill by id", notes = "Input is Bill Id and return bill object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(value = "/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<Bill>> getBillById(@PathVariable String id) {

		return billService.getBillById(id);
	}

	@ApiOperation(value = "Delete Bill By Id", notes = "Input is bill Id and return bill object ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@DeleteMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<Bill>> deleteBillById(@PathVariable String id) {

		return billService.deleteBill(id);
	}

	@ApiOperation(value = "Get All Bills")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<List<Bill>>> getAllBillsById() {

		return billService.getAllBills();
	}

	@ApiOperation(value = "get All Bills by date", notes = "Input is date and return  List of bill objects")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@GetMapping(value = "/date", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<List<Bill>>> getByBrand(@RequestParam LocalDate date) {

		return billService.getAllBillsByDate(date);
	}

}
