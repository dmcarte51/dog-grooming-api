package com.example.doggroomingapi.dogs;
import com.example.doggroomingapi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> findByEmail(String email);
    List<Dog> findByUsername(String username);
}
