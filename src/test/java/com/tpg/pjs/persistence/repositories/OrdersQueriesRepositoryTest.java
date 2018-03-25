package com.tpg.pjs.persistence.repositories;

import com.tpg.pjs.persistence.entities.OrderEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class OrdersQueriesRepositoryTest extends OrdersRepositoryTest {

    @Test
    public void findById() {

        OrderEntity saved = entityManager.persist(order);

        Optional<OrderEntity> actual = queriesRepository.findById(saved.getId());

        assertEquals(saved, actual.get());
    }

    @Autowired
    private OrdersQueriesRepository queriesRepository;
}
