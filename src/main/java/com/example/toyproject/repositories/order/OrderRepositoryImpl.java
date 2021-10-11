package com.example.toyproject.repositories.order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class OrderRepositoryImpl implements OrderRepositoryCustom{

    @PersistenceContext
    private EntityManager em;
}
