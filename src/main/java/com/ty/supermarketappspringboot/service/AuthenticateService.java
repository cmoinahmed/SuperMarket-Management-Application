package com.ty.supermarketappspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ty.supermarketappspringboot.dto.JwtRequest;
import com.ty.supermarketappspringboot.dto.JwtResponse;
import com.ty.supermarketappspringboot.dto.ResponseStructure;
import com.ty.supermarketappspringboot.util.JwtTokenUtil;

@Service
public class AuthenticateService {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService detailsService;

	public ResponseEntity<ResponseStructure<JwtResponse>> authenticate(@RequestBody JwtRequest jwtRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUserEmail(), jwtRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Email or Password", e);
		}
		final CustomUserDetails details = (CustomUserDetails) detailsService
				.loadUserByUsername(jwtRequest.getUserEmail());

		final String jwt = jwtTokenUtil.generateToken(details);

		ResponseStructure<JwtResponse> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCCESS");
		responseStructure.setData(new JwtResponse(jwt, details.getUser().getRole(), details.getUser().getId()));

		return ResponseEntity.ok(responseStructure);
	}
}
