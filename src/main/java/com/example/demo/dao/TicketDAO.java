package com.example.demo.dao;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TicketDAO {
    @Autowired
    private TicketRepository ticketRepository;

    public Ticket save(final Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> findAllByUserIdNotNull() {
        return ticketRepository.findAllByUserIdNotNull();
    }

    public List<Ticket> findAllByUserIdNull() {
        return ticketRepository.findAllByUserIdNull();
    }

    public List<Ticket> findAllByUserId(final Integer userId) {

        return ticketRepository.findAllByUserId(userId);
    }

    public List<Ticket> findByMovieId(final Integer movieId){
        return ticketRepository.findAllByMovieId(movieId);
    }

    public Optional<Ticket> findById(final Integer id) {
        return ticketRepository.findById(id);
    }

    public void deleteById(final Integer id) {
        ticketRepository.deleteById(id);
    }

    public boolean existsById(final Integer id) {
        return ticketRepository.existsById(id);
    }

    public boolean existsByUserId(final Integer userId) {
        return ticketRepository.existsByUserId(userId);
    }
}
