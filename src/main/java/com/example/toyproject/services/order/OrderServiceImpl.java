package com.example.toyproject.services.order;

import com.example.toyproject.common.ResponseCode;
import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.controller.dto.InsertOrder;
import com.example.toyproject.entity.Order;
import com.example.toyproject.entity.Restaurant;
import com.example.toyproject.repositories.order.OrderRepository;
import com.example.toyproject.repositories.restaurant.RestaurantRepository;
import com.example.toyproject.services.common.BaseServiceImpl;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    OrderRepository repository;
    RestaurantRepository restaurantRepository;

    public OrderServiceImpl(OrderRepository repository, RestaurantRepository restaurantRepository) {
        super(repository);
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    @Transactional
    public ResultEntity<Order> insert(InsertOrder insertOrder) {
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
        return new ResultEntity<>(repository.save(order));
    }

    @Override
    public ResultEntity<Order> delete(Long id) {
        Optional<Order> optionalRestaurant = repository.findById(id);

        if(!optionalRestaurant.isPresent()){
            return new ResultEntity<>(ResponseCode.RESTAURANT_NO_RESTAURANT);
        }

        Order order = optionalRestaurant.get();
        order.isDel();
        //repository.delete(order);

        return new ResultEntity<>(order);


    }

    @Override
    public ResultEntity<List<Order>> selectByRestaurantId(Long id) {
        return new ResultEntity<>(repository.findByRestaurantId(id));
    }

    @Override
    public ResultEntity<List<Order>> selectAll() {
        return new ResultEntity<>(repository.findAll());
    }
}
