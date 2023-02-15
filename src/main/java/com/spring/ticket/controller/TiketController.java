package com.spring.ticket.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.ticket.customException.StatusException;
import com.spring.ticket.entities.Ticket;
import com.spring.ticket.entities.status;
import com.spring.ticket.services.TicketServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class TiketController {

    @Autowired
    private TicketServices ticketServices;
    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getTickets()
    {
        log.info("we are fetvhing tickets");
        List<Ticket> lists = ticketServices.getAllTickets();
        if(lists.size() <= 0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(lists));
    }
    @GetMapping("/tickets/{status}")
    public ResponseEntity<List<Ticket>> getTicketsByStatus(@PathVariable("status") String status)
    {
        List<Ticket> lists = ticketServices.getTicketsByStatus(status);
        if(lists.size() <= 0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(lists));
    }



    @PostMapping("/tickets")
    public ResponseEntity<?> addTicket(@RequestBody Ticket ticket)
    {
        //set use
        try
        {
            for(status status1 : status.values())
            {
                if(ticket.getStatus().equals(status1.toString()))
                {
                    try{
                        ticketServices.addTicket(ticket);
                        return ResponseEntity.status(HttpStatus.CREATED).build();
                    }catch(Exception e){
                        e.printStackTrace();
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                }
            }
            throw new StatusException();
        }
        catch(StatusException e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
