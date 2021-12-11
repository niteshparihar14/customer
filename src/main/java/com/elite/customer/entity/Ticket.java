package com.elite.customer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.elite.customer.enums.TicketStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "ticket")

@Data
@AllArgsConstructor
public class Ticket {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Ticket() {
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "u_id")
	private User user;

	@Column(name = "emailAddress")
	private String emailAddress;

	@Column(name = "message")
	private String message;

	@Column(name = "phone")
	private int phone;

	@Column(name = "status")
	private TicketStatus status;

}
