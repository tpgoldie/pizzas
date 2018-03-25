package com.tpg.pjs.persistence.repositories;

import com.tpg.pjs.persistence.entities.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersQueriesRepository extends org.springframework.data.repository.Repository<OrderEntity, Long> {

    Optional<OrderEntity> findById(Long id);
}
