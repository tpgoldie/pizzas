package com.tpg.pjs.services;

import com.tpg.pjs.ordering.OrderDetailsRequest;
import com.tpg.pjs.persistence.repositories.OrdersLifecycleRepository;

public class OrdersServiceImpl {

    private final OrdersLifecycleRepository ordersLifecycleRepository;

    private final PlaceOrder placeOrder;

    public OrdersServiceImpl(OrdersLifecycleRepository ordersLifecycleRepository, PlaceOrder placeOrder) {

        this.ordersLifecycleRepository = ordersLifecycleRepository;

        this.placeOrder = placeOrder;
    }

    public void placeOrder(OrderDetailsRequest request) {

    }
}
