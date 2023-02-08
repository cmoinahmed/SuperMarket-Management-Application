package com.ty.supermarketappspringboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ty.supermarketappspringboot.dto.JwtRequest;
import com.ty.supermarketappspringboot.dto.JwtResponse;
import com.ty.supermarketappspringboot.dto.ResponseStructure;
import com.ty.supermarketappspringboot.service.AuthenticateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin(origins = "*")
public class AuthenticationController {

	@Autowired
	private AuthenticateService authenticateService;

	@ApiOperation(value = "Authenticate Admin By UserName and password", notes = "input jwtRequest --- output jwtResponse obj")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 400, message = "Invalid Username or password") })
	@PostMapping( consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseStructure<JwtResponse>> authenticate(@RequestBody JwtRequest jwtRequest)
			throws Exception {
		return authenticateService.authenticate(jwtRequest);
	}

}