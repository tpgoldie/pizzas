package com.tpg.pjs.services;

import com.tpg.pjs.StringGeneration;
import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.ordering.Order.Status;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

class HandlingAnOrderForProcessing implements StringGeneration {

    private OrderIdGeneration orderIdGeneration;

    static HandlingAnOrderForProcessing given() {

        return new HandlingAnOrderForProcessing();
    }

    private HandlingAnOrderForProcessing() {}

    HandlingAnOrderForProcessing orderProcessing(OrderProcessing orderProcessing) {

        this.orderProcessing = orderProcessing;

        return this;
    }

    HandlingAnOrderForProcessing orderPlacer(OrderPlacer orderPlacer) {

        this.orderPlacer = orderPlacer;

        return this;
    }

    HandlingAnOrderForProcessing orderIdGeneration(OrderIdGeneration orderIdGeneration) {

        this.orderIdGeneration = orderIdGeneration;

        return this;
    }

    HandlingAnOrderForProcessing when() {

        return this;
    }

    HandlingAnOrderForProcessing placingAnOrder(Order order) {

        this.order = order;

        Mockito.when(orderIdGeneration.generateId()).thenReturn(generateString(5));

        actual = orderPlacer.placeOrder(order);

        return this;
    }

    HandlingAnOrderForProcessing then() {

        return this;
    }

    HandlingAnOrderForProcessing aNewOrderProcessIsStarted() {

        verify(orderProcessing).startProcessing(order);

        return this;
    }

    HandlingAnOrderForProcessing orderDetailsStatusIs(Status value) {

        assertEquals(value, actual.getOrderStatus());

        return this;
    }

    HandlingAnOrderForProcessing aNewOrderIdGenerated() {

        verify(orderIdGeneration).generateId();

        return this;
    }

    private OrderProcessing orderProcessing;
    private OrderPlacer orderPlacer;
    private Order order;
    private OrderDetailsStatus actual;
}
