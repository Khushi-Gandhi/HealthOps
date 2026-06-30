package com.khushi.healthops.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequestDTO {

	@NotBlank(message="Email is required")
	@Email(message="Email should not be a valid email address")
	private String email;
	
	@NotBlank(message="Password is required")
	@Size(min=8, message="Password must be at least 8 characters long")
	private String Password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
}