package com.tpg.pjs.services;

import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.ordering.Order.Status;
import com.tpg.pjs.ordering.OrderDetailsRequest;
import com.tpg.pjs.ordering.OrderDetailsRequestFixture;
import com.tpg.pjs.ordering.OrderedPizzaFixture;
import com.tpg.pjs.persistence.entities.OrderEntity;
import com.tpg.pjs.persistence.entities.OrderEntityFixture;
import com.tpg.pjs.persistence.entities.OrderItemEntity;
import com.tpg.pjs.persistence.entities.OrderItemEntityFixture;
import com.tpg.pjs.persistence.repositories.OrdersLifecycleRepository;
import com.tpg.pjs.pizzas.InvalidPizzaException;
import com.tpg.pjs.pizzas.Pizza.Crustiness;
import com.tpg.pjs.pizzas.Pizza.Size;
import com.tpg.pjs.pizzas.PizzaCode;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static com.tpg.pjs.ordering.OrderingAssertions.assertThat;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.mockito.Mockito.verify;

public class PlacingOrders implements OrderDetailsRequestFixture, OrderedPizzaFixture,
        OrderEntityFixture, OrderItemEntityFixture {

    public static PlacingOrders given() {

        return new PlacingOrders();
    }

    private OrderDetailsRequest request;

    private OrderPlacement orderPlacement;

    private OrdersLifecycleRepository ordersLifecycleRepository;

    private OrderingServiceImpl ordersService;

    private ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);

    private ArgumentCaptor<OrderEntity> orderEntityArgumentCaptor = ArgumentCaptor.forClass(OrderEntity.class);

    public PlacingOrders when() {

        return this;
    }

    public PlacingOrders aNewOrderDetailsRequest(String userId, String datetime, PizzaCode pizzaCode, Size size,
                                                 Crustiness crustiness, double price, int quantity) throws InvalidPizzaException {

        request = orderAPizza(userId, datetime, pizzaCode, size, crustiness, price, quantity);

        return this;
    }

    public PlacingOrders placeOrder(OrderPlacement orderPlacement) {

        this.orderPlacement = orderPlacement;

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

    public PlacingOrders theOrdersService(OrderingServiceImpl ordersService) {

        this.ordersService = ordersService;

        return this;
    }

    public PlacingOrders then() {

        return this;
    }

    public PlacingOrders theOrderIsPlaced(Size size, Crustiness crustiness, double price, int quantity) throws InvalidPizzaException {

        verify(orderPlacement).placeOrder(orderArgumentCaptor.capture());

        Order actual = orderArgumentCaptor.getValue();

        assertThat(actual).hasOrderItems(asList(papasFavouriteOrdered(size, crustiness, price, quantity)));

        return this;
    }

    public PlacingOrders theOrderIsSaved() {

        verify(ordersLifecycleRepository).save(orderEntityArgumentCaptor.capture());

        OrderEntity actual = orderEntityArgumentCaptor.getValue();

        OrderEntity orderEntity = newOrderEntity(request.getUserId(), ZonedDateTime.now());

        List<OrderItemEntity> orderItemEntities = request.getOrderedItems().stream()
                .map(value -> newOrderItemEntity(orderEntity, value.getItemTypeCode(),
                        value.getItemCode(), value.getSize(), value.getCrustiness(),
                        new BigDecimal(value.getPrice()), value.getQuantity())).collect(toList());

        orderEntity.setOrderItems(orderItemEntities);

        OrderItemEntity orderItemEntity = orderItemEntities.get(0);

        assertThat(actual).hasOrderItems(singletonList(orderItemEntity));

        return this;
    }

    public PlacingOrders theOrderStatusIs(Status value) {

        assertThat(request).hasOrderStatus(value);

        return this;
    }
}
