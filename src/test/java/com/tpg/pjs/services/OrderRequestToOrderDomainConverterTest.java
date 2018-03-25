package com.tpg.pjs.services;

import com.tpg.pjs.ordering.*;
import com.tpg.pjs.pizzas.InvalidPizzaException;
import com.tpg.pjs.pizzas.Pizza;
import com.tpg.pjs.ordering.OrderedPizzaBuilderSelector;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.tpg.pjs.ordering.Order.Status.PENDING;
import static com.tpg.pjs.ordering.OrderingAssertions.assertThat;
import static com.tpg.pjs.pizzas.Pizza.*;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pjs.pizzas.Pizza.Size.LARGE;
import static com.tpg.pjs.pizzas.PizzaCode.PAPAS_FAVOURITE_CODE;
import static java.util.stream.Collectors.toList;

public class OrderRequestToOrderDomainConverterTest implements OrderDetailsRequestFixture {

    private OrderedPizzaBuilderSelector selector;

    private OrderRequestToOrderDomainConverter converter;

    @Before
    public void setUp() {

        selector = new OrderedPizzaBuilderSelector();

        converter = new OrderRequestToOrderDomainConverter();
    }

    @Test
    public void convertOrderRequestToOrderDomain() throws InvalidPizzaException {

        OrderDetailsRequest request = orderAPizza("jdoe", generateString(5),
                "23/12/2016 12:15:30", PAPAS_FAVOURITE_CODE, LARGE, DEEP_CRUST,
                16.99, 2, PENDING);

        Order actual = converter.convert(request);

        List<OrderItem> orderedItems = request.getOrderedItems().stream()
                .map(this::newOrderItem).filter(Optional::isPresent)
                .map(Optional::get).collect(toList());

        assertThat(actual)
                .hasUserId(request.getUserId())
                .hasSessionId(request.getSessionId())
                .hasOrderItems(orderedItems);
    }

    private Optional<OrderItem> newOrderItem(OrderItemDetails value) {

        Optional<Pizza.Builder> builder = selector.select(value.getItemCode());

        Optional<Pizza> pizza = builder.flatMap(b -> newPizza(value, b));

        return pizza.map(p -> new OrderedPizza.Builder()
                .pizza(p)
                .unitPrice(new BigDecimal(value.getPrice()))
                .quantity(value.getQuantity())
                .build());
    }

    private Optional<Pizza> newPizza(OrderItemDetails value,Pizza.Builder builder) {

        try {
            Pizza pizza = builder.itemCode(value.getItemCode())
                    .crustiness(Crustiness.valueOf(value.getCrustiness()))
                    .size(Size.valueOf(value.getSize()))
                    .build();

            return Optional.of(pizza);
        }
        catch (InvalidPizzaException e) {
            return Optional.empty();
        }
    }
}
