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
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
	@Autowired
	private UserService userService;

	@ApiOperation(value = "Save Admin", notes = "Input is User Object and return Admin object")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED") })
	@PostMapping(value = "/save", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<User>> saveAdmin(@RequestBody @Validated User user) {
		return userService.saveAdmin(user);
	}

	@ApiOperation(value = "Get Admin By Id", notes = "Input is Admin Id and return Admin Object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(value = "/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<User>> getAdminById(@PathVariable String id) {
		return userService.getAdminById(id);
	}

	@ApiOperation(value = "Get All Admin")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@GetMapping(value = "/getall", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<List<User>>> getAllAdmin() {
		return userService.getAllAdmin();
	}

	@ApiOperation(value = "Delete Admin By Id", notes = "Input is Admin Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@DeleteMapping(value = "/delete/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<User>> deleteAdminById(@PathVariable String id) {
		return userService.deleteAdminById(id);
	}

	@ApiOperation(value = "Update Admin", notes = "Input is Admin Object and return Updated Admin object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@PutMapping(value = "/update", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<User>> updateAdmin(@RequestBody User user) {
		return userService.updateAdmin(user);
	}

	@ApiOperation(value = "Validate Admin By Email", notes = "Inputs are Admin's Email Id and Password and return Admin object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PostMapping(value = "/{email}/{password}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<User>> validateAdminByEmail(@PathVariable String email,
			@PathVariable String password) {
		return userService.validateUserByEmail(email, password);
	}

	@ApiOperation(value = "Validate Admin By Phone", notes = "Inputs are Admin's phone number and password and return Admin object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@PutMapping(value = "/{phone}/{password}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<User>> validateAdminByPhone(@PathVariable String phone,
			@PathVariable String password) {
		return userService.validateUserByPhone(phone, password);
	}

	@ApiOperation(value = "Get User By Role", notes = "Input is User Role and return User Object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(value = "/role", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<List<User>>> getUserByRole() {
		return userService.getUserByRole();
	}

}
