package com.example.toyproject.services.restaurant;

import com.example.toyproject.common.ResponseCode;
import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.entity.Restaurant;
import com.example.toyproject.repositories.restaurant.RestaurantRepository;
import com.example.toyproject.services.common.BaseServiceImpl;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class RestaurantServiceImpl extends BaseServiceImpl<Restaurant> implements RestaurantService {

    private RestaurantRepository repository;

    public RestaurantServiceImpl(RestaurantRepository repository) {
        super(repository);
        this.repository = repository;
    }
    @Override
    public ResultEntity<Restaurant> insert(Restaurant restaurant) {
        ResultEntity<Restaurant> resultEntity;

        if(restaurant.getName() == null || restaurant.getName().isEmpty()){
            resultEntity = new ResultEntity<>(ResponseCode.RESTAURANT_NO_NAME);
        } else if(restaurant.getAddress() == null || restaurant.getAddress().isEmpty()){
            resultEntity = new ResultEntity<>(ResponseCode.RESTAURANT_NO_ADDRESS);
        } else if(restaurant.getPhoneNum() == null || restaurant.getPhoneNum().isEmpty()){
            resultEntity = new ResultEntity<>(ResponseCode.RESTAURANT_NO_PHONENUM);
        } else if(repository.findByName(restaurant.getName()).isPresent()){
            resultEntity = new ResultEntity<>(ResponseCode.RESTAURANT_PRESENT_NAME);
        } else {
            resultEntity = new ResultEntity<>(repository.save(restaurant));
        }
        log.info("Restaurant insert end -----");
        return resultEntity;
    }

    @Override
    public ResultEntity<List<Restaurant>> selectAll() {
        log.info("Restaurant selectAll end -----");
        return new ResultEntity<>(repository.findAll());
    }

    @Override
    public ResultEntity<Restaurant> selectOne(Long id) {
        Optional<Restaurant> optionalRestaurant = repository.findById(id);

        if(!optionalRestaurant.isPresent()){
            return new ResultEntity<>(ResponseCode.RESTAURANT_NO_RESTAURANT);
        }

        log.info("Restaurant selectOne end -----");

        return new ResultEntity<>(optionalRestaurant.get());
    }


    @Override
    @Transactional
    public ResultEntity<Restaurant> update(Long id, Restaurant restaurant) {
        Optional<Restaurant> optionalRestaurant = repository.findById(id);

        if(!optionalRestaurant.isPresent()){
            return new ResultEntity<>(ResponseCode.RESTAURANT_NO_RESTAURANT);
        }

        Restaurant getRestaurant = optionalRestaurant.get();
        getRestaurant.modify(restaurant);

        log.info("Restaurant update end -----");

        return new ResultEntity<>(getRestaurant);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public ResultEntity<Restaurant> delete(Long id) {
        Optional<Restaurant> optionalRestaurant = repository.findById(id);

        if(!optionalRestaurant.isPresent()){
            return new ResultEntity<>(ResponseCode.RESTAURANT_NO_RESTAURANT);
        }

        Restaurant restaurant = optionalRestaurant.get();
        restaurant.setDeleteFlag(true);
        //repository.delete(restaurant);

        log.info("Restaurant delete end -----");

        return new ResultEntity<>(restaurant);
    }
}