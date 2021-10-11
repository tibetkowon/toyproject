package com.example.toyproject.model;

import com.example.toyproject.model.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TBL_ORDER")
public class Order extends BaseEntity {

    @Column(name = "CUSTOMERNAME")
    private String customerName;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_RESTAURANT_ID"))
    private Restaurant restaurant;
}
