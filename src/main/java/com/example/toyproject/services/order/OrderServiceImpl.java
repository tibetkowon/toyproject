package com.example.toyproject.services.order;

import com.example.toyproject.controller.dto.InsertOrder;
import com.example.toyproject.model.Order;
import com.example.toyproject.model.Restaurant;
import com.example.toyproject.repositories.order.OrderRepository;
import com.example.toyproject.repositories.restaurant.RestaurantRepository;
import com.example.toyproject.services.common.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    OrderRepository repository;
    RestaurantRepository restaurantRepository;

    public OrderServiceImpl(OrderRepository repository, RestaurantRepository restaurantRepository) {
        super(repository);
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void insert(InsertOrder insertOrder) {
        Restaurant restaurant = restaurantRepository.getById(insertOrder.getRestaurantId());
        Order order = new Order(insertOrder.getCustomerName(),restaurant);

       repository.save(order);
    }

    @Override
    public void delete(Long id) {
        repository.delete(repository.findById(id).orElse(null));
    }

    @Override
    public Order selectOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Order> selectAll() {
        return repository.findAll();
    }
}
