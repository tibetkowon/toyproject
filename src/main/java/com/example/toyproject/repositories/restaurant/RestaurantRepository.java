package com.example.toyproject.repositories.restaurant;

import com.example.toyproject.model.Restaurant;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustom {
    Optional<Restaurant> findByName(String name);

}
