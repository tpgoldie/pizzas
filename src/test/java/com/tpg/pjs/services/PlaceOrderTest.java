package com.tpg.pjs.services;

import com.tpg.pjs.ordering.OrderDetailsRequestFixture;
import com.tpg.pjs.persistence.repositories.OrdersLifecycleRepository;
import com.tpg.pjs.pizzas.InvalidPizzaException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.tpg.pjs.ordering.Order.Status.PENDING;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.ORIGINAL;
import static com.tpg.pjs.pizzas.Pizza.Size.SMALL;
import static com.tpg.pjs.pizzas.PizzaCode.PAPAS_FAVOURITE_CODE;
import static com.tpg.pjs.services.PlacingAnOrder.given;

@RunWith(MockitoJUnitRunner.class)
public class PlaceOrderTest implements OrderDetailsRequestFixture {

    @Mock
    private OrdersLifecycleRepository ordersLifecycleRepository;

    @Mock
    private OrderPlacement orderPlacement;

    private OrderingServiceImpl orderingService;

    @Before
    public void setUp() {

        orderingService = new OrderingServiceImpl(ordersLifecycleRepository, orderPlacement);
    }

    @Test
    public void placeOrder_orderDetails_shouldPlaceNewOrder() throws InvalidPizzaException {

        String sessionId = generateString(5);

        given()
            .orderDetailsRequest("jdoe", sessionId,"12/10/2017 16:57:45", PAPAS_FAVOURITE_CODE, SMALL, ORIGINAL,
                    16.45, 2)
            .orderPlacement(orderPlacement)
            .theOrdersLifecycleRepository(ordersLifecycleRepository)
            .ordersService(orderingService)
        .when()
            .placingAnOrder()
        .then()
            .theOrderIsPlaced(SMALL, ORIGINAL,16.45, 2)
            .theOrderIsSaved()
            .theOrderStatusSessionIdIs(sessionId)
            .theOrderStatusIs(PENDING);
    }
}
