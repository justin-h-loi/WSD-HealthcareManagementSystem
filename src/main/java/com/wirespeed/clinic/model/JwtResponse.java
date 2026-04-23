package com.wirespeed.clinic.model;

/*
Student name:  William 'Bill' McRury
Module 11 - JwtResponse.java
EN.605.789.81.SP26

This Class POJO is used for returning authentication details to the client. 
It packages the security token along with user identity metadata following a successful login:
- Returns the signed JSON Web Token (JWT) to the client application
- Specifies the token type (Bearer) for subsequent HTTP Authorization headers
- Includes user identifiers and assigned security roles for client-side access control

Notes:
- This class is used exclusively by the AuthController during the sign-in flow
- Facilitates stateless authentication by providing all necessary session info in a single payload
*/

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private List<String> roles;

    // The Constructor for AuthController class:
    public JwtResponse(String accessToken, String username, List<String> roles) {
        this.token = accessToken;
        this.username = username;
        this.roles = roles;
    }

    // --- GETTERS / SETTERS ---

    // Retrieve the signed JWT string
    public String getAccessToken() {
        return token;
    }

    // Assign the JWT string
    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    // Retrieve the authorization scheme (typically Bearer)
    public String getTokenType() {
        return type;
    }

    // Update the authorization scheme if necessary
    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    // Retrieve the authenticated username
    public String getUsername() {
        return username;
    }

    // Assign the authenticated username
    public void setUsername(String username) {
        this.username = username;
    }

    // Retrieve the list of granted authorities/roles
    public List<String> getRoles() {
        return roles;
    }

    // Update the list of granted authorities
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}