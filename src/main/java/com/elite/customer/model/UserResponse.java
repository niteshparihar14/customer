package com.elite.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

	private String name;

	private String emailId;
	
	private String phone;

	private String role;
	
	private int status;
	
	private String token;
}
