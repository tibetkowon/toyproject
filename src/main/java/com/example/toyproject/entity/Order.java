package com.example.toyproject.entity;

import com.example.toyproject.controller.dto.order.InsertOrder;
import com.example.toyproject.entity.common.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_ORDER")
//@Where(clause = "IS_DEL='N'")
public class Order extends BaseEntity {

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "MENU_NAME")
    private String menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDER_RESTAURANT_ID"))
    private Restaurant restaurant;

    public Order(InsertOrder insertOrder, Restaurant restaurant){
        this.customerName = insertOrder.getCustomerName();
        this.menu = insertOrder.getMenuName();
        this.restaurant = restaurant;
    }
}
