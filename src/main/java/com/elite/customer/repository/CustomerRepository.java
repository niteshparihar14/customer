package com.elite.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elite.customer.entity.User;

public interface CustomerRepository extends JpaRepository<User, Long> {

	User findByEmailId(String emailId);

}
