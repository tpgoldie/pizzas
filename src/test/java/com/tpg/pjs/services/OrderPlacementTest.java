package com.tpg.pjs.services;

import com.tpg.pjs.StringGeneration;
import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.ordering.OrderItemDetailsFixture;
import com.tpg.pjs.pizzas.Pizza;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.tpg.pjs.ordering.Order.Status.PENDING;
import static com.tpg.pjs.ordering.OrderedItemType.PIZZA;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pjs.pizzas.PizzaCode.AMERICAN_HOT_CODE;
import static com.tpg.pjs.services.ProcessingAnOrder.given;
import static java.util.Collections.singletonList;

@RunWith(MockitoJUnitRunner.class)
public class OrderPlacementTest implements StringGeneration, OrderItemDetailsFixture {

    @Test
    public void placeOrder() {

        Order order = new Order.Builder()
                .dateOrdered("23/12/2016 12:15:30")
                .sessionId(generateString(5))
                .userId("pjs")
                .itemsOrdered(singletonList(newOrderItem(
                        PIZZA.getCode(), AMERICAN_HOT_CODE.getValue(),15.95,
                        3, Pizza.Size.MEDIUM.name(), DEEP_CRUST.name(),
                        true)))
                .build();

        given()
            .orderProcessing(orderProcessing)
            .orderIdGeneration(orderIdGeneration)
            .orderPlacement(orderPlacement)
        .when()
            .placingAnOrder(order)
        .then()
            .aNewOrderProcessIsStarted()
            .aNewOrderIdGenerated()
            .orderDetailsStatusIs(PENDING);
    }

    @Mock
    private OrderIdGeneration orderIdGeneration;

    @Mock
    private OrderProcessing orderProcessing;

    @InjectMocks
    private OrderPlacementImpl orderPlacement;
}
