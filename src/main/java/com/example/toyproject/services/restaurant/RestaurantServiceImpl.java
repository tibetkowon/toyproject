package com.example.toyproject.services.restaurant;

import com.example.toyproject.model.Restaurant;
import com.example.toyproject.repositories.restaurant.RestaurantRepository;
import com.example.toyproject.services.common.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RestaurantServiceImpl extends BaseServiceImpl<Restaurant> implements RestaurantService {

    private RestaurantRepository repository;

    public RestaurantServiceImpl(RestaurantRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void insert(Restaurant restaurant) {
        repository.save(restaurant);
    }

    @Override
    public List<Restaurant> selectAll() {
        return repository.findAll();
    }

    @Override
    public Restaurant selectOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Restaurant selectOne(String name) {;
        return repository.findByName(name).orElse(null);
    }

    //TODO : 업데이트 setter -> 생성자
    @Override
    public void update(String name, Restaurant restaurant) {
        Restaurant getData = repository.findByName(name).orElse(new Restaurant());
        log.info("getData == {}, ID == {}",getData, getData.getId());
        if(restaurant.getName() != null && !restaurant.getName().isEmpty()){
            getData.setName(restaurant.getName());
        }
        if(restaurant.getAddress() != null && !restaurant.getAddress().isEmpty()){
            getData.setAddress(restaurant.getAddress());
        }
        if(restaurant.getPhoneNum() != null && !restaurant.getPhoneNum().isEmpty()){
            getData.setPhoneNum(restaurant.getPhoneNum());
        }
        repository.save(getData);
    }

    @Override
    public void delete(String name) {
        repository.delete(repository.findByName(name).get());
    }
}
