package com.example.toyproject.model;

import com.example.toyproject.model.common.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

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
