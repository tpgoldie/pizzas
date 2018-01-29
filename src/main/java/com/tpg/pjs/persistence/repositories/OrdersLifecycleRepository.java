package com.tpg.pjs.persistence.repositories;

import com.tpg.pjs.persistence.entities.OrderEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersLifecycleRepository {

    void save(OrderEntity order);
}
