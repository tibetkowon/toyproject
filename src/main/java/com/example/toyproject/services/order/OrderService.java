package com.example.toyproject.services.order;

import com.example.toyproject.controller.dto.InsertOrder;
import com.example.toyproject.model.Order;
import com.example.toyproject.services.common.BaseService;
import java.util.List;

public interface OrderService extends BaseService<Order> {
    void insert(InsertOrder insertOrder);

    void delete(Long id);

    Order selectOne(Long id);

    List<Order> selectAll();
}
