package com.elite.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elite.customer.config.JwtUtil;
import com.elite.customer.entity.User;
import com.elite.customer.kafka.source.CustomerEventSource;
import com.elite.customer.model.CustomerEvent.Status;
import com.elite.customer.model.LoanRequestEvent;
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
	
	@Autowired
	private CustomerEventSource event;

	@PostMapping(path = "/user/login")
	public ResponseEntity<UserResponse> addEntity(@RequestBody LoginRequest user) throws JsonProcessingException {
		User existingUser = service.getUserByUserName(user.getEmailId());

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

	@PostMapping(path = "/register")
	public User addUser(@RequestBody User user) {
		return this.service.addUser(user);
	}

	@GetMapping(path = "/user")
	public List<User> getAll() {
		return this.service.getAll();
	}

	@GetMapping(path = "/user/{id}")
	public User getById(@PathVariable("id") Long id) {
		return this.service.getById(id);
	}
	
	
	@PostMapping(path = "/user/loan")
	public ResponseEntity<User> validateCustomer(@RequestBody LoanRequestEvent request) {
		User user = this.service.getById(request.getCustomerId());
		
		if(user == null || user.getCreditScore() < 800) {
			event.publishLoanEvent(user, request.getLoanId(), Status.FAILURE);
			return ResponseEntity.ok(user);
		}
		
		event.publishLoanEvent(user, request.getLoanId(), Status.SUCCESS);
		return ResponseEntity.ok(user);
	}

}
