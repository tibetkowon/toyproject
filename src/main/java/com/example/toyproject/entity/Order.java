package com.example.toyproject.entity;

import com.example.toyproject.entity.common.BaseEntity;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "TBL_ORDER")
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_RESTAURANT_ID"))
    private Restaurant restaurant;


}
