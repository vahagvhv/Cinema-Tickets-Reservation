package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.Hall;
import com.example.demo.model.Movie;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public UserDTO userToUserDTO(final User user) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }

    public User userDTOtoUser(final UserDTO userDTO) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDTO, User.class);
    }

    public User userCreateDTOtoUser(final UserCreateDTO userCreateDTO) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userCreateDTO, User.class);
    }

    public Ticket ticketDTOtoTicket(final TicketDTO ticketDTO) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(ticketDTO, Ticket.class);
    }

    public TicketDTO ticketToTicketDTO(final Ticket ticket) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(ticket, TicketDTO.class);
    }

    public Ticket ticketCreateDTOtoTicket(final TicketCreateDTO ticketCreateDTO) {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(ticketCreateDTO, Ticket.class);
    }

    public MovieDTO movieToMovieDTO(final Movie movie) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movie, MovieDTO.class);
    }

    public Movie movieDTOToMovie(final MovieDTO movieDTO) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movieDTO, Movie.class);
    }

    public Movie movieCreatDTOToMovie(final MovieCreateDTO movieCreateDTO) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movieCreateDTO, Movie.class);
    }

    public HallDTO hallToHallDTO(final Hall hall) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(hall, HallDTO.class);
    }

    public Hall hallDTOToHall(final HallDTO hallDTO) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(hallDTO, Hall.class);
    }

    public Hall hallCreateDTOToHall(final HallCreateDTO hallCreateDTO) {
        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(hallCreateDTO, Hall.class);
    }
}
