package com.example.demo.dao;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovieDAO {
    @Autowired
    private MovieRepository movieRepository;

    public Movie save(final Movie movie){
        return movieRepository.save(movie);
    }
    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(final Integer id) {
        return movieRepository.findById(id);
    }

    public List<Movie> findByRating(final float rating){
        return movieRepository.findAllByRating(rating);
    }

    public void deleteById(final Integer id){
        movieRepository.deleteById(id);
    }

    public boolean existsById(final Integer id){
        return movieRepository.existsById(id);
    }
}
