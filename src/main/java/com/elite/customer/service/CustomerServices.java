package com.elite.customer.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.elite.customer.entity.UserEntity;
import com.elite.customer.repository.CustomerRepository;

@Component
public class CustomerServices {

	@Autowired
	private CustomerRepository repository;

	public UserEntity getUser(Long id) {
		return repository.getById(id);
	}
	
	public UserEntity getUserByUserName(String userName) {
		return repository.findByEmailId(userName);
	}
	
	public boolean verifyPassword(UserEntity user, String password) {
		if (user != null && password != null && StringUtils.equals(password, user.getPassword())) {
			return true;
		}
		return false;
	}
	
	public boolean isActive(UserEntity user) {
		return (user != null && user.getStatus() == 1) ? true : false;
	}
}
