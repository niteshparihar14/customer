package com.elite.customer.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@OneToMany(mappedBy = "user")
	private List<Ticket> ticket;
}
