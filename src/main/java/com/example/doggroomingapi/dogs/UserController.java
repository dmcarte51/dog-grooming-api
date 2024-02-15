package com.example.doggroomingapi.dogs;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<Dog> registerUser(@RequestBody Dog dog) {
        try {
            return ResponseEntity.ok(userService.saveUser(dog));
        } catch (Exception e) {
            return new ResponseEntity(ExceptionUtils.getStackTrace(e), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = "application/json")
    public List<Dog> getUsers() {
            System.out.println("testing");
            return ResponseEntity.ok(userService.getUsers()).getBody();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Dog getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public Dog updateUser(@PathVariable Long id, @RequestBody Dog newDog) {
        Dog oldDog = userService.getUser(id);
        oldDog.setUsername(newDog.getUsername());
        oldDog.setEmail(newDog.getEmail());
        oldDog.setFirstName(newDog.getFirstName());
        oldDog.setLastName(newDog.getLastName());
        oldDog.setPassword(newDog.getPassword());
        oldDog.setPhoneNumber(newDog.getPhoneNumber());
        return oldDog;
    }


}
