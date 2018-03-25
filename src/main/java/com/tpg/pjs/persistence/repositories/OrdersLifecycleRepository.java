package com.tpg.pjs.persistence.repositories;

import com.tpg.pjs.persistence.entities.OrderEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersLifecycleRepository extends org.springframework.data.repository.Repository<OrderEntity, Long> {

    void save(OrderEntity order);
}
