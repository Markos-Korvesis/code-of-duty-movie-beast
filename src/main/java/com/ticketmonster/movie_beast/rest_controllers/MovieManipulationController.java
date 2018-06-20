package com.ticketmonster.movie_beast.rest_controllers;

import com.ticketmonster.movie_beast.custom_exceptions.ResourceNotFoundException;
import com.ticketmonster.movie_beast.models.Movie;
import com.ticketmonster.movie_beast.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Component
@RestController("/movies")
public class MovieManipulationController {

    @Autowired
    MovieRepository movieRepository;

    // Get All Movies
    @GetMapping("/movies/all")
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Get a Single Movie
    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable(value = "id") Integer id) {
        return movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "Id", id));
    }

    // Update a Movie
    @PutMapping("/movies/{id}")
    public Movie updateMovie(@PathVariable(value = "id") Integer id, @Valid @RequestBody Movie movieDetails) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "Id", id));
        movie.setMovie_name(movieDetails.getMovie_name());
        movie.setMovie_description(movieDetails.getMovie_description());

        Movie updatedMovie = movieRepository.save(movie);
        return updatedMovie;
    }

    // Delete a Movie
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable(value = "id") Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "Id", id));

        movieRepository.delete(movie);

        return ResponseEntity.ok().build();
    }
}