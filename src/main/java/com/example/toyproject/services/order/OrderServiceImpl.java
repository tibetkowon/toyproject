package com.example.toyproject.services.order;

import com.example.toyproject.model.Order;
import com.example.toyproject.repositories.order.OrderRepository;
import com.example.toyproject.services.common.BaseServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void insert(Order order) {
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
