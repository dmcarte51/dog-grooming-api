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

    @Transactional
    public Dog saveDog(Dog dog) {
        Dog newDog = dogRepository.saveAndFlush(dog);
        entityManager.refresh(dog);
        return newDog;
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

    public Dog getDogById(Long id) {
        try {
            return dogRepository.findById(id).get();
        } catch(NoSuchElementException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Experimental methods

    public List<Dog> getDogsByUserId(Long user_id) {
        try {
            System.out.println(dogRepository.findDogsByUserId(user_id));
            System.out.println(user_id);
            return dogRepository.findDogsByUserId(user_id);
        } catch(NoSuchElementException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }
}

