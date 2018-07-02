package com.ticketmonster.movie_beast.rest_controllers;

import com.ticketmonster.movie_beast.helpers.handlers.CustomAccessHandler;
import com.ticketmonster.movie_beast.models.User;
import com.ticketmonster.movie_beast.repositories.IBookingRepository;
import com.ticketmonster.movie_beast.repositories.ISeatReservationRepository;
import com.ticketmonster.movie_beast.repositories.IUserRepository;
import com.ticketmonster.movie_beast.services.implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.security.Principal;

@Component
@RestController
public class UserController {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ISeatReservationRepository seatReservationRepository;

    @Autowired
    IBookingRepository bookingRepository;

    @Autowired
    CustomAccessHandler customAccessHandler;

    @Autowired
    UserServiceImpl userService;

    /**
     * Allows the user to Login
     *
     * @param user - email and password
     * @return logged in user
     */
    @PostMapping("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Principal user(Principal user) {
        return user;
    }

    /**
     * Allows the user to create an account
     *
     * @param newUser - requires email, password, fullname
     * @return created user, 201 OR CustomException, 409
     */
    @PostMapping("/register")
    @Consumes("application/json")
    @Produces("application/json")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody User newUser) {
        try {
            return userService.createNewUser(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @return a list of registered users
     */
    @GetMapping("/users")
    @Produces("application/json")
    public ResponseEntity<?> getAllUsers() {
        try {
            return userService.findAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param userId - INTEGER, a user's id
     * @return specified user's details
     */
    @GetMapping("/users/{userId}")
    @Produces("application/json")
    public ResponseEntity<?> getSingleUser(@PathVariable(value = "userId") Integer userId) {
        try {
            return userService.findSingleUser(SecurityContextHolder.getContext().getAuthentication(), userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param userId - INTEGER, a user's id
     * @return specified user's basket of seat reservations
     */
    @GetMapping("/users/{userId}/basket")
    @Produces("application/json")
    public ResponseEntity<?> getUserBasket(@PathVariable(value = "userId") Integer userId) {
        try {
            return userService.getUserBasket(SecurityContextHolder.getContext().getAuthentication(), userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param userId - INTEGER, a user's id
     * @return specified user's bookings
     */
    @GetMapping("/users/{userId}/bookings")
    public ResponseEntity<?> getAllUserBookings(@PathVariable(value = "userId") Integer userId) {
        try {
            return userService.getAllUserBookings(SecurityContextHolder.getContext().getAuthentication(), userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param userId - INTEGER, a user's id
     * @param bookingId - INTEGER, a booking's id
     * @return specified user's specified booking
     */
    @GetMapping("/users/{userId}/bookings/{bookingId}")
    public ResponseEntity<?> getSingleUserBooking(@PathVariable(value = "userId") Integer userId, @PathVariable(value = "bookingId") Integer bookingId) {
        try {
            return userService.getSingleUserBooking(SecurityContextHolder.getContext().getAuthentication(), userId, bookingId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    // Update a User
    @PutMapping("/users/{userId}")
    @Produces("application/json")
    public ResponseEntity<?> updateUser(@PathVariable(value = "userId") Integer userId, @Valid @RequestBody User userDetails) {
        try {
            return userService.updateSingleUser(SecurityContextHolder.getContext().getAuthentication(), userId, userDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Reset user password
    @PutMapping("/users/{userId}/passwordReset")
    public ResponseEntity<?> resetPassword(@PathVariable(value = "userId") Integer userId){
        try{
            return userService.resetPassword(SecurityContextHolder.getContext().getAuthentication(), userId);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Delete a User
    @DeleteMapping("/users/{userId}")
    @Produces("application/json")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "userId") Integer userId) {
        try {
            return userService.deleteUserAndCleanup(SecurityContextHolder.getContext().getAuthentication(), userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
