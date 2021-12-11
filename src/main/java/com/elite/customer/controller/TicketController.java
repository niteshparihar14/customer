package com.elite.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.elite.customer.entity.Ticket;
import com.elite.customer.service.TicketService;

@RestController
@RequestMapping(path = "/api/v1")

public class TicketController {

	@Autowired
	private TicketService service;

	@GetMapping(path = "/ticket")
	public List<Ticket> getAll() {
		return this.service.getAll();
	}

	@GetMapping(path = "/ticket/{userid}")
	public List<Ticket> getById(@PathVariable("userid") Long id) {
		return service.getById(id);
	}

	@PostMapping(path = "/ticket/{userid}")
	public Ticket addTicket(@RequestBody Ticket ticket, @PathVariable("userid") Long userid) {
		return this.service.addTicket(ticket, userid);
	}

}
