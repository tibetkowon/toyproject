package com.example.toyproject.repositories.restaurant;

import com.example.toyproject.entity.restaurant.Restaurant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.orderList o WHERE r.deleteFlag = FALSE")
    List<Restaurant> findAll();

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.orderList o WHERE r.deleteFlag = FALSE AND r.id = ?1")
    Optional<Restaurant> findById(Long id);

    boolean existsByName(String name);
}
