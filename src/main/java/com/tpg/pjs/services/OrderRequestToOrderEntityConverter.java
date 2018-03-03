package com.tpg.pjs.services;

import com.tpg.pjs.ordering.OrderItemDetails;
import com.tpg.pjs.persistence.entities.OrderEntity;
import com.tpg.pjs.persistence.entities.OrderItemEntity;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class OrderRequestToOrderEntityConverter implements RequestToEntityConverter {

    @Override
    public OrderEntity convert(OrderDetailsRequest request) {

        OrderEntity entity = new OrderEntity();

        entity.setUserId(request.getUserId());

        entity.setOrderPlaced(toZonedDateTime(request.getDateOrdered()));

        List<OrderItemEntity> orderItems = request.getOrderedItems().stream()
                .map(value -> newOrderItemEntity(entity, value)).collect(toList());

        entity.setOrderItems(orderItems);

        return entity;
    }

    private OrderItemEntity newOrderItemEntity(OrderEntity order, OrderItemDetails orderItem) {

        OrderItemEntity entity = new OrderItemEntity();

        entity.setOrder(order);
        entity.setItemTypeCode(orderItem.getItemTypeCode());
        entity.setItemCode(orderItem.getItemCode());
        entity.setSize(orderItem.getSize());
        entity.setCrustiness(orderItem.getCrustiness());
        entity.setPrice(new BigDecimal(orderItem.getPrice()));
        entity.setQuantity(orderItem.getQuantity());

        return entity;
    }
}
