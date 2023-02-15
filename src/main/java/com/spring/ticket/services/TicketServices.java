package com.spring.ticket.services;

import com.spring.ticket.dao.TicketRepository;
import com.spring.ticket.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketServices {
    @Autowired
    private TicketRepository ticketRepository;
    public List<Ticket> getAllTickets()
    {
        List<Ticket> lists = (List<Ticket>) ticketRepository.findAll();
        return lists;
    }

    public List<Ticket> getTicketsByStatus(String status)
    {
        List<Ticket> lists = (List<Ticket>) ticketRepository.findByStatus(status);
        return lists;
    }
    public void addTicket(Ticket ticket)
    {
        ticketRepository.save(ticket);
    }
}
