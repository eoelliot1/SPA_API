package com.sparta.spa_api.services;

import com.sparta.spa_api.entities.Spartan;
import com.sparta.spa_api.repository.SpartanRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class SpartanService implements UserDetailsService {

    private final SpartanRepository repository;

    public SpartanService(SpartanRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Spartan spartan = repository.findByUsername(username);

        if (spartan == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        if (spartan.getUsername() == null || spartan.getUsername().isBlank()) {
            throw new UsernameNotFoundException("DB username is null/blank for lookup: " + username);
        }

        if (spartan.getPassword() == null || spartan.getPassword().isBlank()) {
            throw new UsernameNotFoundException("DB password is null/blank for user: " + spartan.getUsername());
        }

        return User.builder()
                .username(spartan.getUsername())
                .password(spartan.getPassword()) // should already be encoded
                .authorities("ROLE_" + spartan.getRole()) // ADD AUTHORITIES
                .build();
    }

}