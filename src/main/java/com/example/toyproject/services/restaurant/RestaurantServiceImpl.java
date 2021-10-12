package com.example.toyproject.services.restaurant;

import com.example.toyproject.common.ResponseCode;
import com.example.toyproject.common.ResultEntity;
import com.example.toyproject.entity.Restaurant;
import com.example.toyproject.repositories.restaurant.RestaurantRepository;
import com.example.toyproject.services.common.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class RestaurantServiceImpl extends BaseServiceImpl<Restaurant> implements RestaurantService {

    private RestaurantRepository repository;

    public RestaurantServiceImpl(RestaurantRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public boolean validate(Restaurant restaurant) {

        return true;
    }

    @Override
    public ResultEntity<Restaurant> insert(Restaurant restaurant) {
        ResultEntity<Restaurant> resultEntity;
        try{
            resultEntity = new ResultEntity(repository.save(restaurant));

        } catch(DataIntegrityViolationException e){
            if(repository.findByName(restaurant.getName()).isPresent()){
                resultEntity = new ResultEntity(ResponseCode.RESTAURANT_PRESENT_NAME.getCode(), ResponseCode.RESTAURANT_PRESENT_NAME.getMessage());

            } else if(restaurant.getName() == null || restaurant.getName().isEmpty()){
                resultEntity = new ResultEntity(ResponseCode.RESTAURANT_NO_NAME.getCode(), ResponseCode.RESTAURANT_NO_NAME.getMessage());

            } else if(restaurant.getAddress() == null || restaurant.getAddress().isEmpty()){
                resultEntity = new ResultEntity(ResponseCode.RESTAURANT_NO_ADDRESS.getCode(), ResponseCode.RESTAURANT_NO_ADDRESS.getMessage());

            } else if(restaurant.getPhoneNum() == null || restaurant.getPhoneNum().isEmpty()){
                resultEntity = new ResultEntity(ResponseCode.RESTAURANT_NO_PHONENUM.getCode(), ResponseCode.RESTAURANT_NO_PHONENUM.getMessage());

            } else {
                resultEntity = new ResultEntity(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMessage());
            }
        } catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return resultEntity;
    }

    @Override
    @Transactional
    public List<Restaurant> selectAll() {
        return repository.findAll();
    }

    @Override
    public Restaurant selectOne(Long id) {
        return repository.findById(id).orElse(null);
    }


    //TODO : 업데이트 setter -> 생성자
    @Override
    public void update(Long id, Restaurant restaurant) {
        Restaurant getData = repository.findById(id).orElse(new Restaurant());
        log.info("getData == {}, ID == {}",getData, getData.getId());


        repository.save(getData);
    }

    @Override
    public void delete(Long id) {
        repository.delete(repository.findById(id).get());
    }
}
