package com.tpg.pjs.services;

import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.ordering.Order.Status;
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
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static com.tpg.pjs.ordering.Order.Status.PENDING;
import static com.tpg.pjs.ordering.OrderingAssertions.assertThat;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class PlacingAnOrder implements OrderDetailsRequestFixture, OrderedPizzaFixture,
        OrderEntityFixture, OrderItemEntityFixture {

    public static PlacingAnOrder given() {

        return new PlacingAnOrder();
    }

    public PlacingAnOrder when() {

        return this;
    }

    public PlacingAnOrder orderDetailsRequest(String userId, String sessionId, String datetime, PizzaCode pizzaCode, Size size,
                                              Crustiness crustiness, double price, int quantity) throws InvalidPizzaException {

        request = orderAPizza(userId, sessionId, datetime, pizzaCode, size, crustiness, price, quantity, PENDING);

        return this;
    }

    public PlacingAnOrder orderPlacement(OrderPlacement orderPlacement) {

        this.orderPlacement = orderPlacement;

        return this;
    }

    public PlacingAnOrder theOrdersLifecycleRepository(OrdersLifecycleRepository ordersLifecycleRepository) {

        this.ordersLifecycleRepository = ordersLifecycleRepository;

        return this;
    }

    public PlacingAnOrder ordersService(OrderingServiceImpl ordersService) {

        this.ordersService = ordersService;

        return this;
    }

    public PlacingAnOrder placingAnOrder() {

        OrderDetailsStatus orderStatusDetails = OrderDetailsStatus
                .builder()
                    .userId(request.getUserId())
                    .orderStatus(PENDING)
                    .sessionId(request.getSessionId())
                    .orderId(generateString(5))
                .build();

        Order order = requestConverter.convert(request);

        Mockito.when(orderPlacement.placeOrder(order)).thenReturn(orderStatusDetails);

        ordersService.placeOrder(request);

        return this;
    }

    public PlacingAnOrder then() {

        return this;
    }

    public PlacingAnOrder theOrderIsPlaced(Size size, Crustiness crustiness, double price, int quantity) throws InvalidPizzaException {

        verify(orderPlacement).placeOrder(orderArgumentCaptor.capture());

        Order actual = orderArgumentCaptor.getValue();

        assertThat(actual).hasOrderItems(asList(papasFavouriteOrdered(size, crustiness, price, quantity)));

        return this;
    }

    public PlacingAnOrder theOrderIsSaved() {

        verify(ordersLifecycleRepository).save(orderEntityArgumentCaptor.capture());

        OrderEntity actual = orderEntityArgumentCaptor.getValue();

        OrderEntity orderEntity = newOrderEntity(request.getUserId(), request.getSessionId(), ZonedDateTime.now(), emptyList());

        List<OrderItemEntity> orderItemEntities = request.getOrderedItems().stream()
                .map(value -> newOrderItemEntity(value.getItemTypeCode(),
                        value.getItemCode(), value.getSize(), value.getCrustiness(),
                        new BigDecimal(value.getPrice()), value.getQuantity())).collect(toList());

        orderEntity.setOrderItems(orderItemEntities);

        orderItemEntities.forEach(item -> item.setOrder(orderEntity));

        OrderItemEntity orderItemEntity = orderItemEntities.get(0);

        assertThat(actual).hasOrderItems(singletonList(orderItemEntity));

        return this;
    }

    PlacingAnOrder theOrderStatusIs(Status value) {

        assertThat(request).hasOrderStatus(value);

        return this;
    }

    PlacingAnOrder theOrderStatusSessionIdIs(String value) {

        assertEquals("Order status ession id does not match", value, request.getSessionId());

        return this;
    }

    private OrderDetailsRequest request;

    private OrderPlacement orderPlacement;

    private final OrderRequestToOrderDomainConverter requestConverter = new OrderRequestToOrderDomainConverter();

    private OrdersLifecycleRepository ordersLifecycleRepository;

    private OrderingServiceImpl ordersService;

    private ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);

    private ArgumentCaptor<OrderEntity> orderEntityArgumentCaptor = ArgumentCaptor.forClass(OrderEntity.class);
}
