package com.example.doggroomingapi.user;
import com.example.doggroomingapi.dogs.Dog;
import com.example.doggroomingapi.validators.PasswordMatches;
import com.example.doggroomingapi.validators.ValidEmail;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Data
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


}
