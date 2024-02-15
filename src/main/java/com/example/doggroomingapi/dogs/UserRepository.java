package com.example.doggroomingapi.dogs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Dog, Long> {

    List<Dog> findByEmail(String email);
    List<Dog> findByUsername(String username);
}
