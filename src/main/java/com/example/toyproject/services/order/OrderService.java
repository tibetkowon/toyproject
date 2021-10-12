package com.example.toyproject.services.order;

import com.example.toyproject.controller.dto.InsertOrderDto;
import com.example.toyproject.entity.Order;
import com.example.toyproject.services.common.BaseService;
import java.util.List;

public interface OrderService extends BaseService<Order> {
    void insert(InsertOrderDto insertOrderDto);

    void delete(Long id);

    Order selectOne(Long id);

    List<Order> selectAll();
}
