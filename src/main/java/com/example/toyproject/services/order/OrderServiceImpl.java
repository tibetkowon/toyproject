package com.example.toyproject.services.order;

import com.example.toyproject.common.ResponseCode;
import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.controller.dto.InsertOrder;
import com.example.toyproject.controller.dto.ResultOrder;
import com.example.toyproject.entity.Order;
import com.example.toyproject.entity.Restaurant;
import com.example.toyproject.repositories.order.OrderRepository;
import com.example.toyproject.repositories.restaurant.RestaurantRepository;
import com.example.toyproject.services.common.BaseServiceImpl;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    OrderRepository repository;
    RestaurantRepository restaurantRepository;

    public OrderServiceImpl(OrderRepository repository, RestaurantRepository restaurantRepository) {
        super(repository);
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public ResultEntity<ResultOrder> insert(InsertOrder insertOrder) {
        if(insertOrder.getCustomerName() == null || insertOrder.getCustomerName().isEmpty()){
            return new ResultEntity<>(ResponseCode.ORDER_NO_CUSTOMER_NAME);
        }
        if(insertOrder.getRestaurantId() == null || insertOrder.getRestaurantId().equals(0L)){
            return new ResultEntity<>(ResponseCode.ORDER_NO_RESTAURANT);
        }
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(insertOrder.getRestaurantId());
        if(!optionalRestaurant.isPresent()){
            return new ResultEntity<>(ResponseCode.ORDER_ABSENT_RESTAURANT);
        }
        Order order = new Order(insertOrder.getCustomerName(), optionalRestaurant.get());
        Order save = repository.save(order);
        return new ResultEntity<>(new ResultOrder(save));
    }

    @Override
    public ResultEntity<ResultOrder> delete(Long id) {
        Optional<Order> optionalOrder = repository.findById(id);

        if(!optionalOrder.isPresent()){
            return new ResultEntity<>(ResponseCode.ORDER_NO_ORDER);
        }

        Order order = optionalOrder.get();
        ResultOrder resultOrder = new ResultOrder(order);
        order.isDel();
        repository.flush();
        //repository.delete(order);

        return new ResultEntity<>(resultOrder);


    }

    @Override
    public ResultEntity<List<ResultOrder>> selectByRestaurantId(Long id) {
        List<Order> orderList = repository.findByRestaurantId(id);
        List<ResultOrder> resultOrderList = orderList.stream()
            .map(order -> new ResultOrder(order))
            .collect(Collectors.toList());
        return new ResultEntity<>(resultOrderList);
    }

    @Override
    public ResultEntity<Map<String, List<ResultOrder>>> selectAll() {
        List<Order> all = repository.findAll();
        List<ResultOrder> collect = all.stream().map(order -> new ResultOrder(order))
            .collect(Collectors.toList());
        Map<String, List<ResultOrder>> listMap = collect.stream()
            .collect(Collectors.groupingBy(ResultOrder::getRestaurantName));
        return new ResultEntity<>(listMap);
    }

}
