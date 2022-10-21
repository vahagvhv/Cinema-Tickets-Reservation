package com.example.demo.service;

import com.example.demo.dao.MovieDAO;
import com.example.demo.dao.TicketDAO;
import com.example.demo.dto.MovieCreateDTO;
import com.example.demo.dto.MovieDTO;
import com.example.demo.model.Movie;
import com.example.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    Converter converter;

    @Autowired
    MovieDAO movieDAO;

    @Autowired
    TicketDAO ticketDAO;

    @Override
    public Optional<MovieDTO> addMovie(final MovieCreateDTO movieCreateDTO) {

        final Movie movie = converter.movieCreatDTOToMovie(movieCreateDTO);
        final Movie newMovie = movieDAO.save(movie);
        if (newMovie.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(converter.movieToMovieDTO(newMovie));
    }

    @Override
    public Optional<MovieDTO> updateExistingMovie(final MovieDTO movieDTO) {

        if (!movieDAO.existsById(movieDTO.getId())) {
            return Optional.empty();
        }

        final Movie movie = converter.movieDTOToMovie(movieDTO);
        final Movie updatedMovie = movieDAO.save(movie);
        final MovieDTO response = converter.movieToMovieDTO(updatedMovie);
        return Optional.of(response);
    }

    @Override
    public Optional<MovieDTO> getMovieById(final Integer id) {

        final Optional<Movie> movie = movieDAO.findById(id);
        if (movie.isEmpty()) {
            return Optional.empty();
        }
        final MovieDTO movieDTO = converter.movieToMovieDTO(movie.get());
        return Optional.of(movieDTO);
    }

    @Override
    public List<MovieDTO> getMovies() {

        final List<Movie> moviesList = movieDAO.findAll();
        if (moviesList.isEmpty()) {
            return new ArrayList<>();
        }

        final List<MovieDTO> moviesDTOList = new ArrayList<>();
        for (Movie movie : moviesList) {
            moviesDTOList.add(converter.movieToMovieDTO(movie));
        }
        return moviesDTOList;
    }

    @Override
    public List<MovieDTO> getMovieByRating(final float rating) {

        final List<Movie> moviesByRating = movieDAO.findByRating(rating);
        if (moviesByRating.isEmpty()) {
            return new ArrayList<>();
        }

        final List<MovieDTO> movieDTOList = new ArrayList<>();
        for (Movie movie : moviesByRating) {
            final MovieDTO movieDTO = converter.movieToMovieDTO(movie);
            movieDTOList.add(movieDTO);
        }
        return movieDTOList;
    }

    @Override
    public boolean deleteMovieById(final Integer movieId) {

        final LocalDateTime currentDate = LocalDateTime.now();
        final List<Ticket> ticketsList = ticketDAO.findByMovieId(movieId);

        if (!ticketsList.isEmpty()) {
            for (Ticket ticket : ticketsList) {
                if (ticket.getMovieDate().isAfter(currentDate)) {
                    return false;
                }
            }
        }

        movieDAO.deleteById(movieId);
        return true;
    }
}
