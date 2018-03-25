package com.tpg.pjs.services;

import com.tpg.pjs.ordering.Order;

public class OrderRequestToOrderDomainConverter implements RequestToDomainConverter {

    @Override
    public Order convert(OrderDetailsRequest request) {

        return new Order.Builder()
                .userId(request.getUserId())
                .sessionId(request.getSessionId())
                .dateOrdered(request.getDateOrdered())
                .itemsOrdered(request.getOrderedItems())
                .build();
    }
}
