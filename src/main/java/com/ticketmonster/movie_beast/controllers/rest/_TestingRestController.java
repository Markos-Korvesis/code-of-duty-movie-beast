package com.ticketmonster.movie_beast.controllers.rest;

import com.ticketmonster.movie_beast.models.Movie;
import com.ticketmonster.movie_beast.repositories.ICityRepository;
import com.ticketmonster.movie_beast.repositories.IMovieRepository;
import com.ticketmonster.movie_beast.repositories.ITheatreRepository;
import com.ticketmonster.movie_beast.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class is used to easily test Business logic through REST.
 * If you need to test something try it here first and then move it to the right file.
 */
@RestController("/testing")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class _TestingRestController {

    @Autowired
    private ICityRepository cityRepository;

    @Autowired
    private IMovieRepository movieRepository;

    @Autowired
    private ITheatreRepository theatreRepository;

    @Autowired
    private IUserRepository userRepository;

    @GetMapping(value = "/test/city/{cityId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> testCityTheatre(@PathVariable(value = "cityId") Integer cityId) {
        return new ResponseEntity<>(cityRepository.getOne(cityId).getTheatres(), HttpStatus.OK);
    }

    @GetMapping(value = "/test/movie/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> testMovieShows(@PathVariable(value = "movieId") Integer movieId) {
        return new ResponseEntity<>(movieRepository.getOne(movieId).getShows(), HttpStatus.OK);
    }

    @GetMapping(value = "/test/theatre/{theatreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> testTheatreMovies(@PathVariable(value = "theatreId") Integer theatreId) {
        return new ResponseEntity<>(theatreRepository.getOne(theatreId).getMovies(), HttpStatus.OK);
    }

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> getAllUsers() {
        return movieRepository.findAll();
    }

}