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

import com.ty.supermarketappspringboot.dto.Customer;
import com.ty.supermarketappspringboot.dto.ResponseStructure;
import com.ty.supermarketappspringboot.service.CustomerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@ApiOperation(value = "Save Customer", notes = "Input is Customer Object and return Customer object")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED") })
	@PostMapping(value = "/save", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
	public ResponseEntity<ResponseStructure<Customer>> registerCustomer(@RequestBody Customer customer) {

		return customerService.registerCustomer(customer);
	}

	@ApiOperation(value = "get customer by id", notes = "Input is Customer Id and return Customer object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })

	@GetMapping(value = "/getbyid/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@PathVariable String id) {

		return customerService.getCustomerById(id);
	}

	@ApiOperation(value = "Delete Customer By Id", notes = "Input is Customer Id and return  ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@DeleteMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(@PathVariable String id) {

		return customerService.deleteCustomer(id);
	}

	@ApiOperation(value = "Get All Customer")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })

	@GetMapping(value = "/getall", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomers() {

		return customerService.getAllCustomer();
	}

	@ApiOperation(value = "Update Customer", notes = "Input is Customer Object and return Updated Customer object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })

	@PutMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestParam String id,
			@RequestBody Customer customer) {

		return customerService.updateCustomer(id, customer);
	}

	@ApiOperation(value = "get customer by phone", notes = "Input is Customer phone and return Customer object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })

	@GetMapping(value = "/getbyphone", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByPhone(@RequestParam String phone) {

		return customerService.getCustomerByPhone(phone);
	}
}
