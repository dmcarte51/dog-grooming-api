package com.example.doggroomingapi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    public UserService userService;

    // Registers a user and logs them in automatically by generating a JWT as the response
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(
            @RequestBody User user
    ) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    // Logs in a registered user and generates a JWT as the response
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(userService.loginUser(request));
    }
}
