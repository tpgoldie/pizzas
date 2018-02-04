package com.tpg.pjs.services;

import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.ordering.OrderDetailsRequest;

public class OrderRequestToOrderDomainConverter implements RequestToDomainConverter {

    @Override
    public Order convert(OrderDetailsRequest request) {

        return new Order.Builder()
                .userId(request.getUserId())
                .dateOrdered(request.getDateOrdered())
                .itemsOrdered(request.getOrderedItems())
                .build();
    }
}
