package com.wirespeed.clinic.model;

/*
Student name:  William 'Bill' McRury
Module 11 - Role.java
EN.605.789.81.SP26

This POJO Class defines the security roles used for authorization within the system. 
It maps specific authority levels to user accounts in the database layer:
- Maps to the "roles" table in the MySQL database
- Supports role-based access control (RBAC) across all API endpoints
- Stores standardized role names such as ROLE_USER, ROLE_MODERATOR, and ROLE_ADMIN

Notes:
- Utilizes database to automatically sequences to next unique number
- Acts as the relational link between User entities and their granted authorities
*/

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    // Primary key identifier with automated increment handling by the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // The unique name of the security role (e.g., ROLE_USER)
    @Column(length = 20)
    private String name;

    // Default constructor for JPA internal instantiation
    public Role() {}
    
    // Overloaded constructor for quick initialization during user registration
    public Role(String name) { this.name = name; }

    // --- GETTERS / SETTERS ---

    // Retrieve the unique role identifier
    public Integer getId() { return id; }

    // Assign the unique role identifier
    public void setId(Integer id) { this.id = id; }

    // Retrieve the descriptive name of the authority
    public String getName() { return name; }

    // Update the descriptive name of the authority
    public void setName(String name) { this.name = name; }
}