package com.tpg.pjs.services;

import com.tpg.pjs.StringGeneration;
import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.ordering.Order.Status;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

class ProcessingAnOrder implements StringGeneration {

    private OrderIdGeneration orderIdGeneration;

    static ProcessingAnOrder given() {

        return new ProcessingAnOrder();
    }

    private ProcessingAnOrder() {}

    ProcessingAnOrder orderProcessing(OrderProcessing orderProcessing) {

        this.orderProcessing = orderProcessing;

        return this;
    }

    ProcessingAnOrder orderPlacement(OrderPlacementImpl orderPlacement) {

        this.orderPlacement = orderPlacement;

        return this;
    }

    ProcessingAnOrder orderIdGeneration(OrderIdGeneration orderIdGeneration) {

        this.orderIdGeneration = orderIdGeneration;

        return this;
    }

    ProcessingAnOrder when() {

        return this;
    }

    ProcessingAnOrder placingAnOrder(Order order) {

        this.order = order;

        Mockito.when(orderIdGeneration.generateId()).thenReturn(generateString(5));

        actual = orderPlacement.placeOrder(order);

        return this;
    }

    ProcessingAnOrder then() {

        return this;
    }

    ProcessingAnOrder aNewOrderProcessIsStarted() {

        verify(orderProcessing).startProcessing(order);

        return this;
    }

    ProcessingAnOrder orderDetailsStatusIs(Status value) {

        assertEquals(value, actual.getOrderStatus());

        return this;
    }

    ProcessingAnOrder aNewOrderIdGenerated() {

        verify(orderIdGeneration).generateId();

        return this;
    }

    private OrderProcessing orderProcessing;
    private OrderPlacementImpl orderPlacement;
    private Order order;
    private OrderDetailsStatus actual;
}
