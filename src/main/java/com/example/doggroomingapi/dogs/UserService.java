package com.example.doggroomingapi.dogs;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    protected EntityManager entityManager;


    public List<Dog> getUsers() {
        return userRepository.findAll();
    }

    public Dog getUser(Long id) {
        try {
            return userRepository.findById(id).get();
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
    public Dog saveUser(Dog dog) {
        dog = userRepository.saveAndFlush(dog);
        entityManager.refresh(dog);
        return dog;
    }

    public boolean deleteUser(Long id) {
        try {
            if(userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return true;
            }
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        }

        return false;
    }
    // Custom findByEmail created in UserRepository
    public boolean emailAvailable(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }
    public boolean usernameAvailable(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }
}

