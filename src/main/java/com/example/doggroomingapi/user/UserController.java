package com.example.doggroomingapi.user;

import org.apache.commons.lang3.exception.ExceptionUtils;
import com.example.doggroomingapi.exceptions.UserAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.saveUser(user));
        } catch (Exception e) {
            return new ResponseEntity(ExceptionUtils.getStackTrace(e), HttpStatus.BAD_REQUEST);
        }
    }

    // // Used for testing
//    @GetMapping(path = "/by-email/{email}")
//    public User getUserByEmail(@PathVariable String email) {
//        System.out.println("testing");
//        return ResponseEntity.ok(userService.findByEmail(email)).getBody();
//    }

    @GetMapping(produces = "application/json")
    public List<User> getUsers() {
            System.out.println("testing");
            return ResponseEntity.ok(userService.getUsers()).getBody();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser) {
        User oldUser = userService.getUser(id);
        oldUser.setUsername(newUser.getUsername());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setPhoneNumber(newUser.getPhoneNumber());
        return oldUser;
    }


}
