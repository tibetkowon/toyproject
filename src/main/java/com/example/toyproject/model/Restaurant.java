package com.example.toyproject.model;

import com.example.toyproject.model.common.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@Table(name = "TBL_RESTAURANT")
public class Restaurant extends BaseEntity {

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONENUM")
    private String phoneNum;
}
