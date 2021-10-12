package com.example.toyproject.entity;

import com.example.toyproject.entity.common.BaseEntity;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.validation.annotation.Validated;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_ORDER")
//@Where(clause = "IS_DEL='N'")
public class Order extends BaseEntity {

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_RESTAURANT_ID"))
    private Restaurant restaurant;
}
