package com.tpg.pjs.persistence.entities;

import java.time.ZonedDateTime;
import java.util.List;

import static java.util.Collections.emptyList;

public interface OrderEntityFixture {

    default OrderEntity newOrderEntity(String userId, String sessionId, ZonedDateTime date, List<OrderItemEntity> orderedItems) {

        OrderEntity entity = new OrderEntity();

        entity.setUserId(userId);
        entity.setSessionId(sessionId);
        entity.setOrderPlaced(date);

        entity.setOrderItems(orderedItems);

        orderedItems.forEach(item -> item.setOrder(entity));

        return entity;
    }
}
