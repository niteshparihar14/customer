package com.elite.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.elite.customer.entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Long> {

	@Query(value = "SELECT * FROM ticketdb WHERE u_id = :id", nativeQuery = true)
	List<Ticket> findByUser(Long id);
}
