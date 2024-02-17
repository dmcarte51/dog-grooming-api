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

    @PostMapping("/add-dog")
    public ResponseEntity<Dog> addDog(@RequestBody Dog dog) {
        try {
            return ResponseEntity.ok(dogService.saveDog(dog));
        } catch (Exception e) {
            return new ResponseEntity(ExceptionUtils.getStackTrace(e), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = "application/json")
    public List<Dog> getDogsByUser(@PathVariable String ) {
            System.out.println("testing");
            return ResponseEntity.ok(dogService.getDogsByUser()).getBody();
    }

//    @GetMapping(path = "/{id}", produces = "application/json")
//    public Dog getDog(@PathVariable Long id) {
//        return dogService.getDog(id);
//    }

//    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
//    public Dog updateDog(@PathVariable Long id, @RequestBody Dog newDog) {
//        Dog oldDog = userService.getDog(id);
//        return oldDog;
//    }


}
