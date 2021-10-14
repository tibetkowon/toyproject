package com.example.toyproject.entity;

import com.example.toyproject.entity.common.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_RESTAURANT", uniqueConstraints = {@UniqueConstraint(name = "UK_RESTAURANT_NAME", columnNames = {"NAME"})})
@Where(clause = "IS_DEL = 'N'")
public class Restaurant extends BaseEntity {

    @NotBlank(message = "RESTAURANT_NO_NAME")
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotBlank(message = "RESTAURANT_NO_ADDRESS")
    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @NotBlank(message = "RESTAURANT_NO_PHONE_NUM")
    @Column(name = "PHONENUM", nullable = false)
    private String phoneNum;

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
}
