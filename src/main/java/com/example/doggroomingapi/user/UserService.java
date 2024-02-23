package com.example.doggroomingapi.user;


import com.example.doggroomingapi.exceptions.UserAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    protected EntityManager entityManager;


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        try {
            return userRepository.findById(id).get();
        } catch(NoSuchElementException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch(NoSuchElementException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Transactional
    public User saveUser(User user) {
        user = userRepository.saveAndFlush(user);
        entityManager.refresh(user);
        return user;
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

    // method for checking if password & matchPassword match
    public boolean passwordsMatch(String password, String matchPassword) {
        return BCrypt.checkpw(password, matchPassword);
    }

    // Custom findByEmail created in UserRepository

    // // Used for testing
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
    public boolean emailAvailable(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }
    public boolean usernameAvailable(String username) {
        return userRepository.findByUsername(username) != null;
    }
}

