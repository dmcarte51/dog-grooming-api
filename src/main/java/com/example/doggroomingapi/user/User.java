package com.example.doggroomingapi.user;

import com.example.doggroomingapi.validators.PasswordMatches;
import com.example.doggroomingapi.validators.ValidEmail;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Table(name = "users")
@Entity
@PasswordMatches
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @NotEmpty
    @Column(name = "password")
    private String password;

    private String matchPassword;

    @NotNull
    @NotEmpty
    @ValidEmail
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @NotEmpty
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    // Constructors
    public User() {
    }

    public User(String username, String firstName, String lastName, String password, String matchPassword, String email, String phoneNumber) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.matchPassword = matchPassword;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchPassword() {
        return matchPassword;
    }

    public void setMatchPassword(String matchPassword) {
        this.matchPassword = matchPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
