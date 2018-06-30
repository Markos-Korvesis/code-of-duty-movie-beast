package com.ticketmonster.movie_beast.rest_controllers;

import com.ticketmonster.movie_beast.helpers._deprecated_custom_exceptions.ResourceNotFoundException;
import com.ticketmonster.movie_beast.models.Theatre;
import com.ticketmonster.movie_beast.repositories.ITheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Component
@RestController
public class TheatreController {

    @Autowired
    ITheatreRepository theatreRepository;

    // Get All Theatres
    @GetMapping("/theatres/all")
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    // Get a Single Theatre
    @GetMapping("/theatres/{id}")
    public Theatre getTheatreById(@PathVariable(value = "id") Integer id) {
        return theatreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Theatre", "Id", id));
    }

    // Update a Theatre
    @PutMapping("/theatres/{id}")
    public Theatre updateTheatre(@PathVariable(value = "id") Integer id, @Valid @RequestBody Theatre TheatreDetails) {
        Theatre theatre = theatreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Theatre", "Id", id));
        theatre.setTheatreName(TheatreDetails.getTheatreName());

        return theatreRepository.save(theatre);
    }

    // Delete a Theatre
    @DeleteMapping("/theatres/{id}")
    public ResponseEntity<?> deleteTheatre(@PathVariable(value = "id") Integer id) {
        Theatre theatre = theatreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Theatre", "Id", id));

        theatreRepository.delete(theatre);

        return ResponseEntity.ok().build();
    }
}
