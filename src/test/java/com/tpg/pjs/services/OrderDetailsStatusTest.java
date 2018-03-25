package com.tpg.pjs.services;

import com.tpg.pjs.StringGeneration;
import com.tpg.pjs.ordering.OrderDetailsRequestFixture;
import com.tpg.pjs.pizzas.InvalidPizzaException;
import com.tpg.pjs.pizzas.Pizza;
import com.tpg.pjs.pizzas.PizzaCode;
import org.junit.Before;
import org.junit.Test;

import static com.tpg.pjs.ordering.Order.Status.PENDING;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pjs.pizzas.Pizza.Size.MEDIUM;
import static com.tpg.pjs.pizzas.PizzaCode.CHICKEN_CLUB_CODE;
import static org.junit.Assert.assertEquals;

public class OrderDetailsStatusTest implements OrderDetailsRequestFixture, StringGeneration {

    @Test
    public void matchesRequest_matchingOrderDetailsRequest_shouldReturnTrue() throws InvalidPizzaException {

        OrderDetailsRequest request = orderAPizza("jdoe", "12/08/2017 18:34", CHICKEN_CLUB_CODE,
                MEDIUM, DEEP_CRUST, 17.99, 3, PENDING);

        OrderDetailsStatus orderDetailsStatus = OrderDetailsStatus.builder()
                .userId("jdoe")
                .orderId(generateString(5))
                .sessionId(request.getSessionId())
                .orderStatus(PENDING)
                .build();
        assertEquals("Order details status does not match order details request",
                true, orderDetailsStatus.matches(request));

    }
}
