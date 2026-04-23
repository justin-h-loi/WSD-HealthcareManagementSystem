package com.wirespeed.clinic.security;

/*
 * Student name:  William 'Bill' McRury
 * UserDetailsServiceImpl.java - Bridge between Database and Spring Security
 */

import com.wirespeed.clinic.model.User;
import com.wirespeed.clinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(">>> DB TRACE: Looking up user for Sign-In: " + username);
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.err.println(">>> DB ERROR: User NOT FOUND during login: " + username);
                    return new UsernameNotFoundException("User Not Found with username: " + username);
                });

        System.out.println(">>> DB TRACE: User found. Building UserDetails principal.");
        return UserDetailsImpl.build(user);
    }
}