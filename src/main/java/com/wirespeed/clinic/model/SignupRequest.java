package com.wirespeed.clinic.model;

import java.util.Set;

/**
 * Student name:  William 'Bill' McRury
 * SignupRequest.java - MVP Identity DTO
 * Handles the initial registration of users, patients, or providers.
 */
public class SignupRequest {

    private String username;
    private String email;
    private String password;
    private Set<String> role;

    // No-args constructor
    public SignupRequest() {
    }

    // Full constructor
    public SignupRequest(String username, String email, String password, Set<String> role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // --- GETTERS AND SETTERS ---

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "SignupRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}