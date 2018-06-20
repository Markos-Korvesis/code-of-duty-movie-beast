package com.ticketmonster.movie_beast._initialization;

import com.ticketmonster.movie_beast.models.Role;
import com.ticketmonster.movie_beast.models.User;
import com.ticketmonster.movie_beast.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
public class UserInitialization {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    @Transactional
    public void init(){
        User user = new User();
        user.setEmail("user@dummy.com");
        user.setFull_name("Dummy Dummyson");
        user.setPassword(bCryptPasswordEncoder.encode("1234"));
        user.setRole(Role.USER.name());
        user.setEnabled(true);
        userRepository.save(user);

        user = new User();
        user.setEmail("admin@dummy.com");
        user.setFull_name("Admin Adminson");
        user.setPassword(bCryptPasswordEncoder.encode("1234"));
        user.setRole(Role.ADMIN.name());
        user.setEnabled(true);
        userRepository.save(user);
    }
}