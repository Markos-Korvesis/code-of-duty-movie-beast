package com.ticketmonster.movie_beast.rest_controllers;

import com.ticketmonster.movie_beast.helpers._deprecated_custom_exceptions.ResourceNotFoundException;
import com.ticketmonster.movie_beast.models.Show;
import com.ticketmonster.movie_beast.repositories.IShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Component
@RestController
public class ShowController {

    @Autowired
    IShowRepository showRepository;

    // Get All Shows
    @GetMapping("/shows")
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    // Get a Single Show
    @GetMapping("/shows/{id}")
    public Show getShowById(@PathVariable(value = "id") Integer id) {
        return showRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Show", "Id", id));
    }

    // Update a Show
    @PutMapping("/shows/{id}")
    public Show updateShow(@PathVariable(value = "id") Integer id, @Valid @RequestBody Show showDetails) {
        Show show = showRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Show", "Id", id));
        show.setAvailableSeats(showDetails.getAvailableSeats());

        return showRepository.save(show);
    }

    // Delete a Show
    @DeleteMapping("/shows/{id}")
    public ResponseEntity<?> deleteShow(@PathVariable(value = "id") Integer id) {
        Show show = showRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Show", "Id", id));

        showRepository.delete(show);

        return ResponseEntity.ok().build();
    }
}
