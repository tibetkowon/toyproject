package com.example.toyproject.services.order;

import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.controller.dto.order.InsertOrder;
import com.example.toyproject.controller.dto.order.ResultOrder;
import java.util.List;
import java.util.Map;

public interface OrderService{
    ResultEntity<ResultOrder> insert(InsertOrder insertOrder);

    ResultEntity<ResultOrder> delete(Long id);

    ResultEntity<List<ResultOrder>> selectByRestaurantId(Long id);

    ResultEntity<Map<String, List<ResultOrder>>> selectAll();

}
