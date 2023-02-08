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
import com.ty.supermarketappspringboot.dto.User;
import com.ty.supermarketappspringboot.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/branchadmin")
@CrossOrigin(origins = "*")
public class BranchAdminController {
	@Autowired
	private UserService userService;

	@ApiOperation(value = "Save BranchAdmin", notes = "Input is User Object and return BranchAdmin object")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED") })
	@PostMapping(value = "/save", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<User>> saveBranchAdmin(@RequestBody @Validated User user) {
		return userService.saveBranchAdmin(user);
	}

	@ApiOperation(value = "Get BranchAdmin By Id", notes = "Input is BranchAdmin Id and return BranchAdmin Object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(value = "/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<User>> getBranchAdminById(@PathVariable String id) {
		return userService.getBranchAdminById(id);
	}

	@ApiOperation(value = "Get All BranchAdmin")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@GetMapping(value = "/getall", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<List<User>>> getAllBranchAdmin() {
		return userService.getAllBranchAdmin();
	}

	@ApiOperation(value = "Delete BranchAdmin By Id", notes = "Input is BranchAdmin Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@DeleteMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<User>> deleteBranchAdminById(@PathVariable String id) {
		return userService.deleteBranchAdminById(id);
	}

	@ApiOperation(value = "Update BranchAdmin", notes = "Input is BranchAdmin Object and return Updated BranchAdmin object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@PutMapping(value = "/update", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<User>> updateBranchAdmin(@RequestBody User user) {
		return userService.updateBranchAdmin(user);
	}

	@ApiOperation(value = "Validate BranchAdmin By Email", notes = "Inputs are BranchAdmin's Email Id and Password and return BranchAdmin object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PostMapping(value = "/{email}/{password}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<User>> validateBranchAdminByEmail(@PathVariable String email,
			@PathVariable String password) {
		return userService.validateUserByEmail(email, password);
	}

	@ApiOperation(value = "Validate BranchAdmin By Phone", notes = "Inputs are BranchAdmin's phone number and password and return BranchAdmin object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@PutMapping(value = "/{phone}/{password}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<User>> validateBranchAdminByPhone(@PathVariable String phone,
			@PathVariable String password) {
		return userService.validateUserByPhone(phone, password);
	}

}
