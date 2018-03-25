package com.tpg.pjs.services;

import com.tpg.pjs.ordering.OrderDetailsRequestFixture;
import com.tpg.pjs.persistence.entities.OrderEntity;
import com.tpg.pjs.persistence.entities.OrderItemEntity;
import com.tpg.pjs.persistence.entities.OrderItemEntityFixture;
import com.tpg.pjs.pizzas.InvalidPizzaException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.tpg.pjs.ordering.Order.Status.PENDING;
import static com.tpg.pjs.ordering.OrderingAssertions.assertThat;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pjs.pizzas.Pizza.Size.LARGE;
import static com.tpg.pjs.pizzas.PizzaCode.PAPAS_FAVOURITE_CODE;
import static java.util.stream.Collectors.toList;

public class OrderRequestToOrderEntityConverterTest implements OrderDetailsRequestFixture, OrderItemEntityFixture {

    private OrderRequestToOrderEntityConverter converter;

    @Before
    public void setUp() {

        converter = new OrderRequestToOrderEntityConverter();
    }

    @Test
    public void convertOrderRequestToOrderEntity() throws InvalidPizzaException {

        OrderDetailsRequest request = orderAPizza("jdoe", generateString(5),
                "23/12/2016 12:15:30", PAPAS_FAVOURITE_CODE, LARGE, DEEP_CRUST,
                15.45, 1, PENDING);


        OrderEntity actual = converter.convert(request);

        List<OrderItemEntity> orderItems = request.getOrderedItems().stream()
                .map(value -> newOrderItemEntity(actual, value.getItemTypeCode(), value.getItemCode(),
                        value.getSize(), value.getCrustiness(), new BigDecimal(value.getPrice()),
                        value.getQuantity())).collect(toList());

        assertThat(actual).hasOrderItems(orderItems);
    }
}
