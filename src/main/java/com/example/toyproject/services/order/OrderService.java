package com.example.toyproject.services.order;

import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.controller.dto.InsertOrder;
import com.example.toyproject.controller.dto.ResultOrder;
import com.example.toyproject.entity.Order;
import com.example.toyproject.services.common.BaseService;
import java.util.List;
import java.util.Map;

public interface OrderService extends BaseService<Order> {
    ResultEntity<ResultOrder> insert(InsertOrder insertOrder);

    ResultEntity<ResultOrder> delete(Long id);

    ResultEntity<List<ResultOrder>> selectByRestaurantId(Long id);

    ResultEntity<Map<String, List<ResultOrder>>> selectAll();

}
