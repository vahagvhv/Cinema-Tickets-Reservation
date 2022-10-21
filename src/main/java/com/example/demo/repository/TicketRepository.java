package com.example.demo.repository;

import com.example.demo.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findAllByUserIdNotNull();

    List<Ticket> findAllByUserIdNull();

    List<Ticket> findAllByUserId(final Integer userId);

    List<Ticket> findAllByMovieId(final Integer movieId);

    boolean existsByUserId(final Integer userId);

    boolean existsById(final Integer id);
}
