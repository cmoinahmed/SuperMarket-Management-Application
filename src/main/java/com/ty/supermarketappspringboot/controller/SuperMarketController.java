package com.ty.supermarketappspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.supermarketappspringboot.dto.ResponseStructure;
import com.ty.supermarketappspringboot.dto.SuperMarket;
import com.ty.supermarketappspringboot.service.SuperMarketService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/supermarket")
@CrossOrigin(origins = "*")
public class SuperMarketController {
	@Autowired
	private SuperMarketService superMarketService;

	@ApiOperation(value = "create supermarket", notes = "Input is SuperMarket Object and return SuperMarket object")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED") })
	@PostMapping(value = "/save", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<SuperMarket>> createSuperMarket(
			@RequestBody @Validated SuperMarket superMarket) {

		return superMarketService.createSuperMarket(superMarket);
	}

	@ApiOperation(value = "get SuperMarket by id", notes = "Input is SuperMarket Id and return SuperMarket object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<SuperMarket>> getSuperMarketById(@PathVariable String id) {

		return superMarketService.getSuperMarket(id);
	}

	@ApiOperation(value = "Delete SuperMarket By Id", notes = "Input is SuperMarket Id and return  ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@DeleteMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<SuperMarket>> deleteCustomer(@PathVariable String id) {

		return superMarketService.deleteSuperMarketById(id);
	}

	@ApiOperation(value = "Get All SuperMarket")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<List<SuperMarket>>> getAllCustomerById() {

		return superMarketService.getAllSuperMarket();
	}

	@ApiOperation(value = "Update SuperMarket", notes = "Input is SuperMarket Object and return Updated SuperMarket object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@PutMapping(value = "/update/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<SuperMarket>> updateSuperMarket(@PathVariable String id,
			@RequestBody SuperMarket superMarket) {

		return superMarketService.updateSuperMarket(id, superMarket);
	}

	@ApiOperation(value = "Validate SuperMarket By email", notes = "Inputs are SuperMarket email and phone number  and return SuperMarket object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@PutMapping(value = "/{email}/{password}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })

	public ResponseEntity<ResponseStructure<SuperMarket>> validateSuperMarketByEmailAndPhone(@PathVariable String email,
			@PathVariable String password) {

		return superMarketService.validateSuperMarketByEmailAndPassword(email, password);
	}

	@ApiOperation(value = "get SuperMarket by Address", notes = "Input is SuperMarket Address and return SuperMarket object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(value = "/address/{address}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<List<SuperMarket>>> getSuperMarketByAddress(@PathVariable String address) {

		return superMarketService.getSuperMarketByAddress(address);
	}

}
