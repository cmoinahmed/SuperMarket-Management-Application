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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.supermarketappspringboot.dto.ResponseStructure;
import com.ty.supermarketappspringboot.dto.User;
import com.ty.supermarketappspringboot.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/staff")
@CrossOrigin(origins = "*")
public class StaffController {
	@Autowired
	private UserService userService;

	@ApiOperation(value = "Save Staff", notes = "Input is User Object and return Staff object")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED") })

	@PostMapping(value = "/save", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })

	public ResponseEntity<ResponseStructure<User>> saveStaff(@RequestBody @Validated User user) {
		return userService.saveStaff(user);
	}

	@ApiOperation(value = "Get Staff By Id", notes = "Input is Staff Id and return Satff Object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<User>> getStaffById(@PathVariable String id) {
		return userService.getStaffById(id);
	}

	@ApiOperation(value = "Get All Staff")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@GetMapping(value = "/getall", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<List<User>>> getAllStaff() {
		return userService.getAllStaff();
	}

	@ApiOperation(value = "Delete Staff By Id", notes = "Input is Staff Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })

	@DeleteMapping(value = "/delete", produces = { MediaType.APPLICATION_JSON_VALUE,

			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<User>> deleteStaffById(@RequestParam String id) {
		return userService.deleteStaffById(id);
	}

	@ApiOperation(value = "Update Staff", notes = "Input is Staff Object and return Updated Staff object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@PutMapping(value = "/update", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<User>> updateStaff(@RequestBody @Validated User user) {
		return userService.updateStaff(user);
	}

	@ApiOperation(value = "Validate Staff By Email", notes = "Inputs are Staff's Email Id and Password and return Staff object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PostMapping(value = "/{email}/{password}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
	public ResponseEntity<ResponseStructure<User>> validateStaffByEmail(@PathVariable String email,
			@PathVariable String password) {
		return userService.validateUserByEmail(email, password);
	}

	@ApiOperation(value = "Validate Staff By Phone", notes = "Inputs are Staff's phone number and password and return Staff object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Data Not Found") })
	@PutMapping(value = "/{phone}/{password}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<User>> validateStaffByPhone(@PathVariable String phone,
			@PathVariable String password) {
		return userService.validateUserByPhone(phone, password);
	}

}
