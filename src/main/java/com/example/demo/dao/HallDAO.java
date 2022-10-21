package com.example.demo.dao;

import com.example.demo.model.Hall;
import com.example.demo.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HallDAO {
    @Autowired
    private HallRepository hallRepository;

    public Hall save(final Hall hall) {
        return hallRepository.save(hall);
    }

    public boolean existById(final Integer hallId) {
        return hallRepository.existsById(hallId);
    }

    public List<Hall> findAll() {
        return hallRepository.findAll();
    }

    public Optional<Hall> findById(final Integer id) {
        return hallRepository.findById(id);
    }

    public void deleteHall(final Integer hallId) {
        hallRepository.deleteById(hallId);
    }
}
