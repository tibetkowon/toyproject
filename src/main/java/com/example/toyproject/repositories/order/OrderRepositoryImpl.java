package com.example.toyproject.repositories.order;

import com.example.toyproject.entity.restaurant.Restaurant;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class OrderRepositoryImpl implements OrderRepositoryCustom{

    @PersistenceContext
    EntityManager em;

    @Override
    public int deleteByRestaurant(Restaurant restaurant) {
        StringBuffer q = new StringBuffer(""
            + " UPDATE Order o "
            + " SET o.deleteFlag = 'Y' "
            + " WHERE o.restaurant = :restaurant ");

        return em.createQuery(q.toString())
            .setParameter("restaurant",restaurant)
            .executeUpdate();
    }

}
