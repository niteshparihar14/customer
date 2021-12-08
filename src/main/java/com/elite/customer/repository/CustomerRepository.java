package com.elite.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elite.customer.entity.UserEntity;

public interface CustomerRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmailId(String emailId);

}
