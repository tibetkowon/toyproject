package com.example.toyproject.services.common;

import com.example.toyproject.model.common.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
    private JpaRepository<T, Long> repository;

    public BaseServiceImpl(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }
}
