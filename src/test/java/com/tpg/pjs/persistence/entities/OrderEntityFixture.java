package com.tpg.pjs.persistence.entities;

import java.time.ZonedDateTime;
import java.util.List;

import static java.util.Collections.emptyList;

public interface OrderEntityFixture {

    default OrderEntity newOrderEntity(String userId, ZonedDateTime date) {

        OrderEntity entity = new OrderEntity();

        entity.setUserId(userId);
        entity.setOrderPlaced(date);
        entity.setOrderItems(emptyList());

        return entity;
    }

    default OrderEntity newOrderEntity(String userId, ZonedDateTime date, List<OrderItemEntity> orderedItems) {

        OrderEntity entity = newOrderEntity(userId, date);

        entity.setOrderItems(orderedItems);

        return entity;
    }
}
