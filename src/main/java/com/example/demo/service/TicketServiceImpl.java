package com.example.demo.service;

import com.example.demo.dao.HallDAO;
import com.example.demo.dao.MovieDAO;
import com.example.demo.dao.TicketDAO;
import com.example.demo.dto.*;
import com.example.demo.model.Hall;
import com.example.demo.model.Movie;
import com.example.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private HallDAO hallDAO;

    @Autowired
    private MovieDAO movieDAO;

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private Converter converter;

    @Override
    public Optional<TicketDTO> addTicket(final TicketCreateDTO ticketCreateDTO) {

        if (ticketCreateDTO.getHallId() == null || ticketCreateDTO.getMovieId() == null) {
            System.out.println("hallId and movieId are required");
            return Optional.empty();
        }

        final Optional<Hall> hall = hallDAO.findById(ticketCreateDTO.getHallId());
        if (hall.isEmpty()) {
            System.out.println("Hall with given hallId not found");
            return Optional.empty();
        }

        final Optional<Movie> movie = movieDAO.findById(ticketCreateDTO.getMovieId());
        if (movie.isEmpty()) {
            System.out.println("Movie with given movieId not found");
            return Optional.empty();
        }

        int row_count = hall.get().getRow_count();
        int place_count = hall.get().getPlace_count();
        if (ticketCreateDTO.getRow() < 1 || ticketCreateDTO.getRow() > row_count
                || ticketCreateDTO.getPlace() < 1 || ticketCreateDTO.getPlace() > place_count) {
            System.out.println("Row or place is not valid");
            return Optional.empty();
        }

        List<Ticket> allTickets = ticketDAO.findAll();
        for (Ticket ticket : allTickets) {
            if (ticket.getRow() == ticketCreateDTO.getRow()
                    && ticket.getPlace() == ticketCreateDTO.getPlace()
                    && ticket.getUser() != null && ticket.getUser().getId() != null
            ) {
                System.out.println("Ticket with given row and place is already reserved");
                return Optional.empty();
            }
        }

        final Ticket ticket = converter.ticketCreateDTOtoTicket(ticketCreateDTO);

        final Ticket newTicket = ticketDAO.save(ticket);
        if (newTicket.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(converter.ticketToTicketDTO(newTicket));
    }

    @Override
    public Optional<TicketDTO> updateExistingTicket(final TicketDTO ticketDTO) {

        if (!ticketDAO.existsById(ticketDTO.getId())) {
            return Optional.empty();
        }

        if (ticketDTO.getHallId() == null || ticketDTO.getMovieId() == null) {
            System.out.println("hallId and movieId are required");
            return Optional.empty();
        }

        final Optional<Hall> hall = hallDAO.findById(ticketDTO.getHallId());
        if (hall.isEmpty()) {
            System.out.println("Hall with given hallId not found");
            return Optional.empty();
        }

        final Optional<Movie> movie = movieDAO.findById(ticketDTO.getMovieId());
        if (movie.isEmpty()) {
            System.out.println("Movie with given movieId not found");
            return Optional.empty();
        }

        int row_count = hall.get().getRow_count();
        int place_count = hall.get().getPlace_count();
        if (ticketDTO.getRow() < 1 || ticketDTO.getRow() > row_count
                || ticketDTO.getPlace() < 1 || ticketDTO.getPlace() > place_count) {
            System.out.println("Row or place is not valid");
            return Optional.empty();
        }

        List<Ticket> allTickets = ticketDAO.findAll();
        for (Ticket ticket : allTickets) {
            if (ticket.getRow() == ticketDTO.getRow()
                    && ticket.getPlace() == ticketDTO.getPlace()
                    && ticket.getUser() != null && ticket.getUser().getId() != null
                    && ticket.getUser().getId() != ticketDTO.getUserId()) {
                System.out.println("Ticket with given row and place is already reserved");
                return Optional.empty();
            }
        }

        final Ticket ticket = converter.ticketDTOtoTicket(ticketDTO);
        final Ticket updatedTicket = ticketDAO.save(ticket);
        final TicketDTO response = converter.ticketToTicketDTO(updatedTicket);
        return Optional.of(response);
    }

    @Override
    public Optional<TicketDTO> getTicketById(final Integer id) {

        final Optional<Ticket> ticket = ticketDAO.findById(id);
        if (ticket.isEmpty()) {
            return Optional.empty();
        }
        final TicketDTO ticketDTO = converter.ticketToTicketDTO(ticket.get());

        return Optional.of(ticketDTO);
    }

    @Override
    public List<TicketDTO> getTicketsByFilter(final Boolean isReserved) {

        final List<Ticket> ticketsList;
        if (isReserved == null) {
            ticketsList = ticketDAO.findAll();
        } else if (!isReserved) {
            ticketsList = ticketDAO.findAllByUserIdNull();
        } else {
            ticketsList = ticketDAO.findAllByUserIdNotNull();
        }

        if (ticketsList.isEmpty()) {
            return new ArrayList<>();
        }

        final List<TicketDTO> ticketDTOList = new ArrayList<>();
        for (Ticket ticket : ticketsList) {
            ticketDTOList.add(converter.ticketToTicketDTO(ticket));
        }
        return ticketDTOList;
    }

    @Override
    public void deleteTicketByID(final Integer id) {
        ticketDAO.deleteById(id);
    }

    public List<UserMovieDTO> findTicketAndMovieDetailsByUserId(final Integer userId) {

        final List<Ticket> tickets = ticketDAO.findAllByUserId(userId);
        if (tickets.isEmpty()) {
            return new ArrayList<>();
        }

        final List<UserMovieDTO> userMovieDTOs = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getMovie() == null || ticket.getMovie().getId() == null) {
                continue;
            }

            final UserMovieDTO userMovieDTO = new UserMovieDTO();
            userMovieDTO.setTicketId(ticket.getId());
            userMovieDTO.setRow(ticket.getRow());
            userMovieDTO.setPlace(ticket.getPlace());
            userMovieDTO.setPrice(ticket.getPrice());
            userMovieDTO.setMovieDate(ticket.getMovieDate());
            userMovieDTO.setHallId(ticket.getHall().getId());
            userMovieDTO.setUserId(userId);
            userMovieDTO.setMovieId(ticket.getMovie().getId());
            userMovieDTO.setMovieName(ticket.getMovie().getMovieName());
            userMovieDTO.setDuration(ticket.getMovie().getDuration());
            userMovieDTOs.add(userMovieDTO);
        }

        System.out.println("Found " + userMovieDTOs.size() + " ticket(s) for userId " + userId);
        return userMovieDTOs;
    }
}
