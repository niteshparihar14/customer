package com.elite.customer.model;

import lombok.Data;

@Data
public class LoginRequest {

	private String emailId;
	
	private String password;
}
