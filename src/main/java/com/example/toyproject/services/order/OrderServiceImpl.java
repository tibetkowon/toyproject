package com.example.toyproject.services.order;

import com.example.toyproject.common.ResponseCode;
import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.controller.dto.order.InsertOrder;
import com.example.toyproject.controller.dto.order.ResultOrder;
import com.example.toyproject.entity.Order;
import com.example.toyproject.entity.Restaurant;
import com.example.toyproject.repositories.order.OrderRepository;
import com.example.toyproject.repositories.restaurant.RestaurantRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository repository;
    RestaurantRepository restaurantRepository;

    public OrderServiceImpl(OrderRepository repository, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    @Transactional
    public ResultEntity<ResultOrder> insert(InsertOrder insertOrder) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(insertOrder.getRestaurantId());
        if(optionalRestaurant.isEmpty()) {
            return new ResultEntity<>(ResponseCode.ORDER_ABSENT_RESTAURANT);
        }

        Order order = new Order(insertOrder, optionalRestaurant.get());
        return new ResultEntity<>(new ResultOrder(repository.save(order)));
    }

    @Override
    @Transactional
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
    @Transactional(readOnly = true)
    public ResultEntity<List<ResultOrder>> selectByRestaurantId(Long id) {
        if(!restaurantRepository.existsById(id)){
            return new ResultEntity<>(ResponseCode.ORDER_ABSENT_RESTAURANT);
        }
        List<Order> orderList = repository.findByRestaurantId(id);
        List<ResultOrder> resultOrderList = orderList.stream()
            .map(order -> new ResultOrder(order))
            .collect(Collectors.toList());
        return new ResultEntity<>(resultOrderList);
    }

    @Override
    @Transactional(readOnly = true)
    public ResultEntity<Map<String, List<ResultOrder>>> selectAll() {
        List<Order> all = repository.findAll();
        List<ResultOrder> collect = all.stream().map(order -> new ResultOrder(order))
            .collect(Collectors.toList());
        Map<String, List<ResultOrder>> listMap = collect.stream()
            .collect(Collectors.groupingBy(ResultOrder::getRestaurantName));
        return new ResultEntity<>(listMap);
    }

}
