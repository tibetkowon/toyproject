package com.example.toyproject.services.order;

import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.controller.dto.InsertOrder;
import com.example.toyproject.entity.Order;
import com.example.toyproject.services.common.BaseService;
import java.util.List;

public interface OrderService extends BaseService<Order> {
    ResultEntity<Order> insert(InsertOrder insertOrder);

    ResultEntity<Order> delete(Long id);

    ResultEntity<List<Order>> selectByRestaurantId(Long id);

    ResultEntity<List<Order>> selectAll();
}
