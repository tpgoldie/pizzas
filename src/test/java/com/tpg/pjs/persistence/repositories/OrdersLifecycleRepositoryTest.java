package com.tpg.pjs.persistence.repositories;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrdersLifecycleRepositoryTest extends OrdersRepositoryTest {

    @Test
    public void save() {

        lifecycleRepository.save(order);
    }

    @Autowired
    private OrdersLifecycleRepository lifecycleRepository;
}
