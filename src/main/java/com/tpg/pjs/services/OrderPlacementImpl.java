package com.tpg.pjs.services;

import com.tpg.pjs.ordering.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.tpg.pjs.ordering.Order.Status.PENDING;

@Service
public class OrderPlacementImpl implements OrderPlacement {

    @Override
    public OrderDetailsStatus placeOrder(Order order) {

        orderProcessing.startProcessing(order);

        return OrderDetailsStatus.builder()
                .orderId(orderIdGeneration.generateId())
                .userId(order.getUserId())
                .sessionId(order.getSessionId())
                .orderStatus(PENDING)
                .build();
    }

    @Autowired
    public OrderPlacementImpl(OrderIdGeneration orderIdGeneration, OrderProcessing orderProcessing) {

        this.orderIdGeneration = orderIdGeneration;

        this.orderProcessing = orderProcessing;
    }

    private final OrderIdGeneration orderIdGeneration;
    private OrderProcessing orderProcessing;
}
