package com.example.toyproject.model;

import com.example.toyproject.model.common.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@Table(name = "TBL_RESTAURANT", uniqueConstraints = {@UniqueConstraint(
        name = "RESTAURANT_UNIQUE",
        columnNames = {"NAME","ADDRESS"}
)})
public class Restaurant extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONENUM")
    private String phoneNum;
}
