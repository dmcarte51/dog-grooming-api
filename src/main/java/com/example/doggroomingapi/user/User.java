package com.example.doggroomingapi.user;

import com.example.doggroomingapi.dogs.Dog;
import com.example.doggroomingapi.validators.PasswordMatches;
import com.example.doggroomingapi.validators.ValidEmail;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Table(name = "users")
@Entity
@PasswordMatches
public class User {

    @Id
    @Column(name = "id", nullable = false, unique = true)
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

    @NotNull
    @NotEmpty
    @Column(name = "match_password")
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

//    @OneToMany(mappedBy = "user")
//    private Set<Dog> dogs;

    // Constructors


    public User() {
    }

    public User(Long id, String username, String firstName, String lastName, String password, String matchPassword, String email, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.matchPassword = matchPassword;
        this.email = email;
        this.phoneNumber = phoneNumber;
//        this.dogs = dogs;
    }

//    public Set<Dog> getDogs() {
//        return dogs;
//    }
//
//    public void setDogs(Set<Dog> dogs) {
//        this.dogs = dogs;
//    }

    public void setId(Long id) {
        this.id = id;
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
