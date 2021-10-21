package com.example.toyproject.entity.restaurant;

import com.example.toyproject.controller.dto.restaurant.InsertRestaurant;
import com.example.toyproject.entity.common.BaseEntity;
import com.example.toyproject.entity.order.Order;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_RESTAURANT", uniqueConstraints = {@UniqueConstraint(name = "UK_RESTAURANT_NAME", columnNames = {"NAME"})})
//@Where(clause = "IS_DEL = 'N'")
public class Restaurant extends BaseEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "PHONENUM", nullable = false)
    private String phoneNum;

    @OneToMany(targetEntity = Order.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    private List<Order> orderList;

    public Restaurant(InsertRestaurant restaurant){
        this.name = restaurant.getName();
        this.address = restaurant.getAddress();
        this.phoneNum = restaurant.getPhoneNumber();
    }

    public void modify(Restaurant restaurant){
        if(restaurant.getName() != null && !restaurant.getName().isEmpty()){
            this.name = restaurant.getName();
        }
        if(restaurant.getAddress() != null && !restaurant.getAddress().isEmpty()){
            this.address = restaurant.getAddress();
        }
        if(restaurant.getPhoneNum() != null && !restaurant.getPhoneNum().isEmpty()){
            this.phoneNum = restaurant.getPhoneNum();
        }
    }
    public void isDel(){
        super.isDel();
        this.name = this.name + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS"));
    }
}
