package com.wirespeed.clinic.service;

/*
 * Student name:  William 'Bill' McRury
 */

import com.wirespeed.clinic.model.Role;
import com.wirespeed.clinic.model.User;
import com.wirespeed.clinic.model.SignupRequest;
import com.wirespeed.clinic.repository.RoleRepository;
import com.wirespeed.clinic.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Transactional
    public void registerUser(SignupRequest signUpRequest) {
        System.out.println(">>> TRACE: Entering registerUser for: " + signUpRequest.getUsername());

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            System.out.println(">>> TRACE: Username check failed (already exists)");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Username is already taken!");
        }

        System.out.println(">>> TRACE: Checkpoint 1 - Creating User Entity");
        User user = new User(signUpRequest.getUsername(), 
                           signUpRequest.getEmail(),
                           encoder.encode(signUpRequest.getPassword()));

        System.out.println(">>> TRACE: Checkpoint 2 - Mapping Roles");
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            System.out.println(">>> TRACE: strRoles was NULL - Assigning ROLE_USER");
            roles.add(fetchRole("ROLE_USER"));
        } else {
            strRoles.forEach(role -> {
                System.out.println(">>> TRACE: Processing input role: " + role);
                switch (role.toLowerCase().trim()) {
                    case "admin" -> roles.add(fetchRole("ROLE_ADMIN"));
                    case "provider" -> roles.add(fetchRole("ROLE_PROVIDER"));
                    default -> roles.add(fetchRole("ROLE_USER"));
                }
            });
        }

        user.setRoles(roles);
        System.out.println(">>> TRACE: Checkpoint 3 - Persisting User to DB");
        userRepository.save(user);
        System.out.println(">>> TRACE: SUCCESS - User saved.");
    }

    private Role fetchRole(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> {
                    System.out.println(">>> FATAL: Role " + roleName + " not found in DB!");
                    return new RuntimeException("Role " + roleName + " missing");
                });
    }
}