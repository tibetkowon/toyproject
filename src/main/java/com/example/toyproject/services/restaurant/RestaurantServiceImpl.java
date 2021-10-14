package com.example.toyproject.services.restaurant;

import com.example.toyproject.common.ResponseCode;
import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.controller.dto.ResultRestaurant;
import com.example.toyproject.entity.Order;
import com.example.toyproject.entity.Restaurant;
import com.example.toyproject.repositories.order.OrderRepository;
import com.example.toyproject.repositories.restaurant.RestaurantRepository;
import com.example.toyproject.services.common.BaseServiceImpl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class RestaurantServiceImpl extends BaseServiceImpl<Restaurant> implements RestaurantService {

    private RestaurantRepository repository;
    private OrderRepository orderRepository;

    public RestaurantServiceImpl(RestaurantRepository repository,OrderRepository orderRepository) {
        super(repository);
        this.repository = repository;
        this.orderRepository = orderRepository;
    }
    @Override
    public ResultEntity<ResultRestaurant> insert(Restaurant restaurant) {
        if(repository.existsByName(restaurant.getName())){
            return new ResultEntity<>(ResponseCode.RESTAURANT_PRESENT_NAME);
        }
        ResultRestaurant resultRestaurant = new ResultRestaurant(repository.save(restaurant));

        return new ResultEntity<>(resultRestaurant);
    }

    @Override
    public ResultEntity<List<ResultRestaurant>> selectAll() {
        List<Restaurant> all = repository.findAll();
        List<ResultRestaurant> restaurants = all.stream()
            .map(restaurant -> new ResultRestaurant(restaurant)).collect(Collectors.toList());
        return new ResultEntity<>(restaurants);
    }

    @Override
    public ResultEntity<ResultRestaurant> selectOne(Long id) {
        Optional<Restaurant> optionalRestaurant = repository.findById(id);

        if(!optionalRestaurant.isPresent()){
            return new ResultEntity<>(ResponseCode.RESTAURANT_NO_RESTAURANT);
        }
        return new ResultEntity<>(new ResultRestaurant(optionalRestaurant.get()));
    }


    @Override
    @Transactional
    public ResultEntity<ResultRestaurant> update(Long id, Restaurant restaurant) {
        Optional<Restaurant> optionalRestaurant = repository.findById(id);

        if(!optionalRestaurant.isPresent()){
            return new ResultEntity<>(ResponseCode.RESTAURANT_NO_RESTAURANT);
        }

        Restaurant getRestaurant = optionalRestaurant.get();
        getRestaurant.modify(restaurant);

        return new ResultEntity<>(new ResultRestaurant(getRestaurant));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ResultEntity<ResultRestaurant> delete(Long id) {
        Optional<Restaurant> optionalRestaurant = repository.findById(id);

        if(!optionalRestaurant.isPresent()){
            return new ResultEntity<>(ResponseCode.RESTAURANT_NO_RESTAURANT);
        }
        List<Order> orders = orderRepository.findByRestaurantId(id);
        orders.stream().forEach(order -> order.isDel());

        Restaurant restaurant = optionalRestaurant.get();
        restaurant.isDel();
        //repository.delete(restaurant);

        return new ResultEntity<>(new ResultRestaurant(restaurant));
    }
}