package com.example.toyproject.repositories.order;

import com.example.toyproject.entity.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o join fetch o.restaurant where o.restaurant.id = ?1")
    List<Order> findByRestaurantId(Long id);

    @Query("select o from Order o join fetch o.restaurant")
    List<Order> findAll();

    @Query("select o from Order o join fetch o.restaurant where o.id = ?1")
    Optional<Order> findById(Long id);
}
