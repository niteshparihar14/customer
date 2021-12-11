package com.elite.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elite.customer.entity.Ticket;
import com.elite.customer.entity.User;
import com.elite.customer.repository.CustomerRepository;
import com.elite.customer.repository.TicketRepo;

@Service
public class TicketService {
	
	@Autowired
	private TicketRepo Repo;
	
	@Autowired
	private CustomerRepository userRepo;
	
	public List<Ticket> getAll(){

		return this.Repo.findAll();
	}
	
	public Ticket save(Ticket ticket) {

		return this.Repo.save(ticket);
	}
	
	
	public List<Ticket> getById(Long id) {
		
		/*
		 * User user= userRepo.findById(userid).get(); Long u_id=user.getUserid()
		 */;
		
		return Repo.findByUser(id);
	}

	public Ticket addTicket(Ticket ticket, Long userid) {
		// TODO Auto-generated method stub
		//Ticket ticket1 =new Ticket();
		
		if(ticket!= null && userid != null) {
			
		User user= userRepo.findById(userid).get();
		if(user== null) {
			throw new RuntimeException("User not founfd");
		}
		ticket.setUser(user);
		}
		return Repo.save(ticket);
	}


}
