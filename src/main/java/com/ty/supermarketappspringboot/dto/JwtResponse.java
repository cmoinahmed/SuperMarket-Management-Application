package com.ty.supermarketappspringboot.dto;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class JwtResponse {

	private String jwtToken;
	
	private String role;
	
	private String id;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JwtResponse(String jwtToken, String role, String id) {
		super();
		this.jwtToken = jwtToken;
		this.role = role;
		this.id = id;
	}

}
