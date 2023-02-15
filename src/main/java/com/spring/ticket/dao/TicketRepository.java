package com.spring.ticket.dao;

import com.spring.ticket.entities.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket,Integer> {
        public List<Ticket> findByStatus(String status);
}
