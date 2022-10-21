package com.example.demo.service;

import com.example.demo.dto.MovieCreateDTO;
import com.example.demo.dto.MovieDTO;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    public List<MovieDTO> getMovies();

    public Optional<MovieDTO> getMovieById(Integer id);

    public List<MovieDTO> getMovieByRating(float rating);

    public boolean deleteMovieById(Integer id);

    public Optional<MovieDTO> addMovie(MovieCreateDTO movieCreateDTODTO);

    public Optional<MovieDTO> updateExistingMovie(MovieDTO movieDTO);
}
