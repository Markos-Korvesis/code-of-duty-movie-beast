package com.ticketmonster.movie_beast.services._interfaces;

import com.ticketmonster.movie_beast.models.Booking;
import com.ticketmonster.movie_beast.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IBookingService {

    ResponseEntity<?> bookAllInBasket(Authentication authentication);

    ResponseEntity<?> cancelSingleTicket(Booking booking);

    ResponseEntity<?> getAllBookings(Authentication authentication);

    ResponseEntity<?> getSingleBooking(Authentication authentication, Booking booking);

    ResponseEntity<?> updateSingleBooking(Authentication authentication, Booking booking, Booking newBooking);

    ResponseEntity<?> deleteSingleBooking(Authentication authentication, Booking booking);

    void printTickets(User user, HttpServletResponse res, HttpServletRequest req);

    void printTicketReport(User user, HttpServletResponse res, HttpServletRequest req);
}
