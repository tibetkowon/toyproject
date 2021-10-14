package com.example.toyproject.repositories.restaurant;

import com.example.toyproject.entity.Restaurant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByName(String name);

    boolean existsByName(String name);
}
