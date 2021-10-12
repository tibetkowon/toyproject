package com.example.toyproject.entity;

import com.example.toyproject.entity.common.BaseEntity;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_RESTAURANT", uniqueConstraints = {@UniqueConstraint(name = "UK_RESTAURANT_NAME", columnNames = {"NAME"})})
public class Restaurant extends BaseEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "PHONENUM", nullable = false)
    private String phoneNum;

    public void modify(Long id, Restaurant restaurant){

    }
}
