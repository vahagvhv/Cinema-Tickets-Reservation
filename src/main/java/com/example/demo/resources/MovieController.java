package com.example.demo.resources;

import com.example.demo.dto.MovieCreateDTO;
import com.example.demo.dto.MovieDTO;
import com.example.demo.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity addMovie(final @RequestBody(required = true) MovieCreateDTO movieCreateDTO) {

        final Optional<MovieDTO> movieDTO = movieService.addMovie(movieCreateDTO);
        if (movieDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movie creation failed");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(movieDTO.get());
    }

    @PutMapping
    public ResponseEntity updateMovie(final @RequestBody MovieDTO movieDTO) {

        final Optional<MovieDTO> updatedMovie = movieService.updateExistingMovie(movieDTO);
        if (updatedMovie.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movie update failed");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedMovie.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity getMovieById(final @PathVariable(required = true) Integer id) {

        final Optional<MovieDTO> movieById = movieService.getMovieById(id);
        if (movieById.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Movie with given id not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(movieById);
    }

    @GetMapping("/rating")
    public ResponseEntity getMovieByRating(final @RequestParam(required = true) float rating) {

        final List<MovieDTO> moviesByRating = movieService.getMovieByRating(rating);
        if (moviesByRating.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Movies with given rating not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(moviesByRating);
    }

    @GetMapping("/all")
    public ResponseEntity getAllMovies() {

        final List<MovieDTO> movies = movieService.getMovies();
        if (movies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Movies not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(movies);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMovieById(final @PathVariable(required = true) Integer id) {

        final boolean isDeleted = movieService.deleteMovieById(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movie cannot be deleted as ticket for given movie id found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Movie successfully deleted");
    }
}
