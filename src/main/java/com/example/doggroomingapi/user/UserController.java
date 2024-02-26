package com.example.doggroomingapi.user;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import com.example.doggroomingapi.exceptions.UserAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.Errors;
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

    // USER PRIVILEGE
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(
            @RequestBody User user
    ) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    // USER PRIVILEGE
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(userService.loginUser(request));
    }
}
