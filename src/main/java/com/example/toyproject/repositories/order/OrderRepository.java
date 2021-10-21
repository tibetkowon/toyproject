package com.example.toyproject.repositories.order;

import com.example.toyproject.entity.order.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom{
    @Query("SELECT o FROM Order o JOIN FETCH o.restaurant WHERE o.restaurant.id = ?1 AND o.deleteFlag = FALSE")
    List<Order> findByRestaurantId(Long id);

    @Query("SELECT o FROM Order o JOIN FETCH o.restaurant WHERE o.deleteFlag = FALSE")
    List<Order> findAll();

    @Query("select o from Order o join fetch o.restaurant where o.id = ?1")
    Optional<Order> findById(Long id);
}
