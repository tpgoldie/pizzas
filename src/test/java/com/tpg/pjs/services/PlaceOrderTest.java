package com.tpg.pjs.services;

import com.tpg.pjs.ordering.OrderDetailsRequest;
import com.tpg.pjs.ordering.OrderDetailsRequestFixture;
import com.tpg.pjs.persistence.repositories.OrdersLifecycleRepository;
import com.tpg.pjs.pizzas.InvalidPizzaException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.tpg.pjs.ordering.Order.Status.PENDING;
import static com.tpg.pjs.ordering.OrderingAssertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PlaceOrderTest implements OrderDetailsRequestFixture {

    @Mock
    private OrdersLifecycleRepository ordersLifecycleRepository;

    @Mock
    private PlaceOrder placeOrder;

    @InjectMocks
    private OrdersServiceImpl ordersService;

    @Test
    public void placeOrder_orderDetails_shouldPlaceNewOrder() throws InvalidPizzaException {
        given()
                .aNewOrderDetailsRequest("jdoe")
                .placeOrder(placeOrder)
                .theOrdersLifecyclerepository(ordersLifecycleRepository)
        .when()
                .placingANewOrder()
        .then()
                .theOrderIsPlaced()
                .theOrderIsSaved()
                .theOrderStatusIs(PENDING);

        OrderDetailsRequest request = newOrderDetailsRequest("jdoe");

        ordersService.placeOrder(request);

        assertThat(request).hasOrderStatus(PENDING);
    }
}
