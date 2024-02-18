package com.example.doggroomingapi.dogs;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/dogs")
public class DogController {

    @Autowired
    public DogService dogService;

    @PostMapping(path = "/add-dog")
    public ResponseEntity<Dog> addDog(@RequestBody Dog dog) {
        try {
            return ResponseEntity.ok(dogService.saveDog(dog));
        } catch (Exception e) {
            return new ResponseEntity(ExceptionUtils.getStackTrace(e), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public List<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }

    @GetMapping(path = "/{dog_id}", produces = "application/json")
    public Dog getDogsById(@PathVariable Long dog_id) {
            return ResponseEntity.ok(dogService.getDogById(dog_id)).getBody();
    }

    @GetMapping(path = "/by-user/{user_id}", produces = "application/json")
    public List<Dog> getDogByUser(@PathVariable Long user_id) {
        return dogService.getDogsByUserId(user_id);
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public Dog updateDog(@PathVariable Long id, @RequestBody Dog newDog) {
        Dog oldDog = dogService.getDogById(id);
        oldDog = newDog;
        return ResponseEntity.ok(oldDog).getBody();
    }

}
