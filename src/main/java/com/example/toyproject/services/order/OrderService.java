package com.example.toyproject.services.order;

import com.example.toyproject.model.Order;
import com.example.toyproject.services.common.BaseService;

import java.util.List;

public interface OrderService extends BaseService<Order> {
    public void insert(Order order);

    public void delete(Long id);

    public Order selectOne(Long id);

    public List<Order> selectAll();
}
