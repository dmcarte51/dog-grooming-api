package com.example.doggroomingapi.user;


import com.example.doggroomingapi.exceptions.UserAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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


//    public User registerNewUserAccount(User user) {
//        try {
//
//        } catch (UserAlreadyExistsException e) {
//
//        }
//    }

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
    // Custom findByEmail created in UserRepository
    public boolean emailAvailable(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }
    public boolean usernameAvailable(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }
}

