package com.example.toyproject.services.order;

import com.example.toyproject.model.Order;
import com.example.toyproject.services.common.BaseServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    public OrderServiceImpl(JpaRepository<Order, Long> repository) {
        super(repository);
    }
}
