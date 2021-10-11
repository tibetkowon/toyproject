package com.example.toyproject.repositories.restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class RestaunrantRepositoryImpl implements RestaurantRepositoryCustom{

    @PersistenceContext
    private EntityManager em;
}
