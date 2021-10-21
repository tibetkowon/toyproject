package com.example.toyproject.services.restaurant;

import com.example.toyproject.common.ResponseCode;
import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.controller.dto.restaurant.InsertRestaurant;
import com.example.toyproject.controller.dto.restaurant.RestaurantInfo;
import com.example.toyproject.controller.dto.restaurant.ResultRestaurant;
import com.example.toyproject.entity.restaurant.Restaurant;
import com.example.toyproject.repositories.order.OrderRepository;
import com.example.toyproject.repositories.restaurant.RestaurantRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository repository;
    private OrderRepository orderRepository;

    public RestaurantServiceImpl(RestaurantRepository repository,OrderRepository orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }
    @Override
    @Transactional
    public ResultEntity<ResultRestaurant> insert(InsertRestaurant insertRestaurant) {
        if(repository.existsByName(insertRestaurant.getName())){
            return new ResultEntity<>(ResponseCode.RESTAURANT_PRESENT_NAME);
        }
        Restaurant restaurant = new Restaurant(insertRestaurant);
        ResultRestaurant resultRestaurant = new ResultRestaurant(repository.save(restaurant));

        return new ResultEntity<>(resultRestaurant);
    }

    @Override
    @Transactional(readOnly = true)
    public ResultEntity<List<RestaurantInfo>> selectAll() {
        List<Restaurant> all = repository.findAll();
        List<RestaurantInfo> restaurants = all.stream()
            .map(restaurant -> new RestaurantInfo(restaurant)).collect(Collectors.toList());
        return new ResultEntity<>(restaurants);
    }

    @Override
    @Transactional(readOnly = true)
    public ResultEntity<RestaurantInfo> selectOne(Long id) {
        Optional<Restaurant> optionalRestaurant = repository.findById(id);

        if(!optionalRestaurant.isPresent()){
            return new ResultEntity<>(ResponseCode.RESTAURANT_NO_RESTAURANT);
        }

        Restaurant restaurant = optionalRestaurant.get();

        if(restaurant.isDeleteFlag()){
            return new ResultEntity<>(ResponseCode.RESTAURANT_DEL_RESTAURANT);
        }

        return new ResultEntity<>(new RestaurantInfo(restaurant));
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ResultEntity<ResultRestaurant> update(Long id, Restaurant restaurant) {
        Optional<Restaurant> optionalRestaurant = repository.findById(id);

        if(!optionalRestaurant.isPresent()){
            return new ResultEntity<>(ResponseCode.RESTAURANT_NO_RESTAURANT);
        }

        Restaurant getRestaurant = optionalRestaurant.get();

        if(restaurant.isDeleteFlag()){
            return new ResultEntity<>(ResponseCode.RESTAURANT_DEL_RESTAURANT);
        }

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

        Restaurant restaurant = optionalRestaurant.get();

        if(restaurant.isDeleteFlag()){
            return new ResultEntity<>(ResponseCode.RESTAURANT_DEL_RESTAURANT);
        }

        if(!restaurant.getOrderList().isEmpty()) {
            log.info("delete Order Count == {}",orderRepository.deleteByRestaurant(restaurant));
        }

        restaurant.isDel();
        //repository.delete(restaurant);

        return new ResultEntity<>(new ResultRestaurant(restaurant));
    }
}