package com.elite.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elite.customer.config.JwtUtil;
import com.elite.customer.entity.UserEntity;
import com.elite.customer.model.LoginRequest;
import com.elite.customer.model.UserResponse;
import com.elite.customer.service.CustomerServices;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {

	@Autowired
	private CustomerServices service;
	
	@Autowired
	private JwtUtil utils;

	@PostMapping(path = "/user/login")
	public ResponseEntity<UserResponse> addEntity(@RequestBody LoginRequest user) throws JsonProcessingException {
		UserEntity existingUser = service.getUserByUserName(user.getEmailId());

		if (existingUser == null) {
			throw new RuntimeException("User not exist");
		}

		if (!service.isActive(existingUser)) {
			throw new RuntimeException("User not active");
		}

		if (!this.service.verifyPassword(existingUser, existingUser.getPassword())) {
			throw new RuntimeException("Invalid password");
		}
		
		String token = utils.generateToken(existingUser.getId().toString());
		UserResponse response = new UserResponse();
		response.setEmailId(existingUser.getEmailId());
		response.setName(existingUser.getName());
		response.setPhone(existingUser.getPhone());
		response.setRole(existingUser.getRole());
		response.setStatus(existingUser.getStatus());
		response.setToken(token);

		return ResponseEntity.ok(response);
	}
}
