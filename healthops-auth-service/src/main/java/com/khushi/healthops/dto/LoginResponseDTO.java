package com.khushi.healthops.dto;

public class LoginResponseDTO {
	
	private final String token;

	public LoginResponseDTO(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}
