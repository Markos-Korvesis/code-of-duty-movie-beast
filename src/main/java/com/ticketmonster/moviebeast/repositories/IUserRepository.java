package com.ticketmonster.moviebeast.repositories;

import com.ticketmonster.moviebeast.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}