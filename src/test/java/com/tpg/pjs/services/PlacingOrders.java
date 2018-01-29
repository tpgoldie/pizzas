package com.tpg.pjs.services;

import com.tpg.pjs.ordering.*;
import com.tpg.pjs.persistence.entities.OrderEntity;
import com.tpg.pjs.persistence.repositories.OrdersLifecycleRepository;
import com.tpg.pjs.pizzas.InvalidPizzaException;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import static com.tpg.pjs.ordering.OrderingAssertions.assertThat;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.verify;

public class PlacingOrders implements OrderDetailsRequestFixture, OrderedPizzaFixture {

    public static PlacingOrders given() {

        return new PlacingOrders();
    }

    private OrderDetailsRequest request;

    private PlaceOrder placeOrder;

    private OrdersLifecycleRepository ordersLifecycleRepository;

    private OrdersServiceImpl ordersService;

    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor;

    @Captor
    private ArgumentCaptor<OrderEntity> orderEntityArgumentCaptor;

    public PlacingOrders when() {

        return this;
    }

    public PlacingOrders then() {

        return this;
    }

    public PlacingOrders aNewOrderDetailsRequest(String userId) throws InvalidPizzaException {

        request = newOrderDetailsRequest(userId);

        return this;
    }

    public PlacingOrders placeOrder(PlaceOrder placeOrder) {

        this.placeOrder = placeOrder;

        return this;
    }

    public PlacingOrders theOrdersLifecycleRepository(OrdersLifecycleRepository ordersLifecycleRepository) {

        this.ordersLifecycleRepository = ordersLifecycleRepository;

        return this;
    }

    public PlacingOrders placingANewOrder() {

        ordersService.placeOrder(request);

        return this;
    }

    public PlacingOrders theOrdersService(OrdersServiceImpl ordersService) {

        this.ordersService = ordersService;

        return this;
    }

    public PlacingOrders theOrderIsPlaced() throws InvalidPizzaException {

        verify(placeOrder).placeOrder(orderArgumentCaptor.capture());

        Order actual = orderArgumentCaptor.getValue();

        assertThat(actual).hasOrderItems(singletonList(orderedPizza()));

        return this;
    }

    public PlacingOrders theOrderIsSaved() {

        ordersLifecycleRepository.save(orderEntityArgumentCaptor.capture());

        OrderEntity actual = orderEntityArgumentCaptor.getValue();

        assertThat(actual).hasOrderItems(singletonList(orderedPizza()));

        return this;
    }
}
