package com.example.demo.service;

import com.example.demo.dto.TicketCreateDTO;
import com.example.demo.dto.TicketDTO;
import com.example.demo.dto.UserMovieDTO;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    public List<TicketDTO> getTicketsByFilter(Boolean isReserved);

    public Optional<TicketDTO> getTicketById(Integer id);

    public Optional<TicketDTO> addTicket(TicketCreateDTO ticketCreateDTO);

    public void deleteTicketByID(Integer id);

    public List<UserMovieDTO> findTicketAndMovieDetailsByUserId(Integer userId);

    public Optional<TicketDTO> updateExistingTicket(TicketDTO ticketDTO);
}
