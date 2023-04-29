package com.example.demo.config;


import com.example.demo.User;
import com.example.demo.UserInfoRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final UserInfoRepository repo;


    public PersonDetailsService(UserInfoRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> users = repo.findByName(username);

        if (users.isEmpty()){
            throw new UsernameNotFoundException("Now user");
        }

        return new PersonDetails(users.get());
    }
}
