package com.example.demo.resources;

import com.example.demo.dto.TicketCreateDTO;
import com.example.demo.dto.TicketDTO;
import com.example.demo.dto.UserMovieDTO;
import com.example.demo.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity addTicket(final @RequestBody(required = true) TicketCreateDTO ticketCreateDTO) {

        final Optional<TicketDTO> ticketDTO = ticketService.addTicket(ticketCreateDTO);
        if (ticketDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket creation failed");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketDTO.get());
    }

    @PutMapping
    public ResponseEntity updateTicket(final @RequestBody TicketDTO ticketDTO) {

        final Optional<TicketDTO> updatedTicket = ticketService.updateExistingTicket(ticketDTO);
        if (updatedTicket.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket update failed");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedTicket.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity getTicketById(final @PathVariable(required = true) Integer id) {

        final Optional<TicketDTO> ticketById = ticketService.getTicketById(id);
        if (ticketById.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Ticket with given id not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ticketById);
    }

    @GetMapping("/all")
    public ResponseEntity getAllTickets(final @RequestParam(required = false) Boolean isReserved) {

        final List<TicketDTO> tickets = ticketService.getTicketsByFilter(isReserved);
        if (tickets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Tickets not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tickets);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTicket(final @PathVariable(required = true) Integer id) {

        ticketService.deleteTicketByID(id);
        return ResponseEntity.status(HttpStatus.OK).body("Ticket successfully deleted");
    }

    @GetMapping("/details")
    public ResponseEntity getTicketAndMovieDetailsByUserId(final @RequestParam(required = true) Integer userId) {

        final List<UserMovieDTO> ticketByUserId = ticketService.findTicketAndMovieDetailsByUserId(userId);
        if (ticketByUserId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Ticket for given user id not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ticketByUserId);
    }
}
