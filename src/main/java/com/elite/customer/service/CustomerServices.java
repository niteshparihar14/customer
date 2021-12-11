package com.elite.customer.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.elite.customer.entity.User;
import com.elite.customer.repository.CustomerRepository;

@Component
public class CustomerServices {

	@Autowired
	private CustomerRepository repository;

	public User getUser(Long id) {
		return repository.getById(id);
	}
	
	public User getUserByUserName(String userName) {
		return repository.findByEmailId(userName);
	}
	
	public boolean verifyPassword(User user, String password) {
		if (user != null && password != null && StringUtils.equals(password, user.getPassword())) {
			return true;
		}
		return false;
	}
	
	public boolean isActive(User user) {
		return (user != null && user.getStatus() == 1) ? true : false;
	}

	public User addUser(User user) {
		return repository.save(user);
	}

	public List<User> getAll() {
		return repository.findAll();
	}

	public User getById(Long id) {
		return repository.findById(id).get();
	}
}
