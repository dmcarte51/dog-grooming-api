package com.example.doggroomingapi.dogs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {


    List<Dog> findDogsByUserId(Long user_id);
}
