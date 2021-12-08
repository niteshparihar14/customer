package com.elite.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.elite.customer.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "emailid")
	private String emailId;
	
	@Column(name = "phone")
	private String phone;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role;
	
	@Column(name = "status")
	private int status;
}
