package com.ticketmonster.movie_beast.services._interfaces;

import com.ticketmonster.movie_beast.models.Show;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface IShowService {

    ResponseEntity<?> getAllShows();

    ResponseEntity<?> getSingleShow(Show show);

    ResponseEntity<?> createNewShow(Show newShow, Authentication authentication);

    ResponseEntity<?> updateSingleShow(Show show, Show showDetails, Authentication authentication);

    ResponseEntity<?> deleteSingleShow(Show show, Authentication authentication);
}