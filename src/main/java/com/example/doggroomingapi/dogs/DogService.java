package com.example.doggroomingapi.dogs;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DogService {
    @Autowired
    private DogRepository dogRepository;

    @PersistenceContext
    protected EntityManager entityManager;


    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    public List<Dog> getDogsByUser(Long id) {
        try {
            return dogRepository.findById(id).get();
        } catch(NoSuchElementException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }


//    public Dog registerNewUserAccount(Dog user) {
//        try {
//
//        } catch (UserAlreadyExistsException e) {
//
//        }
//    }

    @Transactional
    public Dog saveDog(Dog dog) {
        dog = dogRepository.saveAndFlush(dog);
        entityManager.refresh(dog);
        return dog;
    }

    public boolean deleteDog(Long id) {
        try {
            if(dogRepository.existsById(id)) {
                dogRepository.deleteById(id);
                return true;
            }
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }
}

