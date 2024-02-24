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

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User user
    ) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(userService.loginUser(request));
    }

//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody User user) {
//        try {
//            User savedUser = userService.saveUser(user);
//            return ResponseEntity.ok(savedUser);
//        } catch (Exception e) {
//            String errorMessage = "Failed to register user: " + e.getMessage();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
//        }
//    }

    @GetMapping(produces = "application/json")
    public List<User> getUsers() {
            System.out.println("testing");
            return ResponseEntity.ok(userService.getUsers()).getBody();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public User getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id)).getBody();
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User newUser) {
        User oldUser = userService.getUser(id);

        if (oldUser == null) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if user not found
        }

        oldUser.setUsername(newUser.getUsername());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setPhoneNumber(newUser.getPhoneNumber());

        userService.saveUser(oldUser); // Assuming saveUser updates the user in the database

        return ResponseEntity.ok(oldUser);
    }


}
