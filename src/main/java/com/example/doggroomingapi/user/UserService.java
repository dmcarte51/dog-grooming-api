package com.example.doggroomingapi.user;


import com.example.doggroomingapi.config.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    protected EntityManager entityManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


//        Lines 64-76 are responsible for saving a new user.
//        The remaining code is responsible for generating a token with the user details inserted into
//        the payload/data section. This part is basically optional as this is the equivalent of logging
//        in the user immediately after registering, but it improves the UX.
    @Transactional
    public AuthenticationResponse saveUser(User user) {
        user = User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(passwordEncoder.encode(user.getPassword()))
                .matchPassword(passwordEncoder.encode(user.getMatchPassword()))
                .phoneNumber(user.getPhoneNumber())
                .username(user.getUsername())
                .role((user.getRole()))
                .build();
        userRepository.saveAndFlush(user);
        entityManager.refresh(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    @Transactional
    public AuthenticationResponse loginUser(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }


    public boolean emailAvailable(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }
    public boolean usernameAvailable(String username) {
        return userRepository.findByUsername(username) != null;
    }
}

