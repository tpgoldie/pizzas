package com.tpg.pizzas.domain.ordering;

import com.tpg.pizzas.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.tpg.pizzas.domain.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pizzas.domain.ordering.OrderingAssertions.assertThat;

public class OrderedPizzaBuilderTest {

    private OrderedPizza.Builder builder;

    @Before
    public void setUp() {

        builder = new OrderedPizza.Builder();
    }

    @Test
    public void build() throws InvalidPizzaException {

        AmericanHot pizza = (AmericanHot) Pizzas.americanHot().crustiness(DEEP_CRUST).build();

        OrderedPizza actual = builder
            .unitPrice(new BigDecimal(16.99))
            .quantity(3)
            .pizza(pizza)
            .build();

        assertThat(actual)
            .hasPizza(pizza)
            .hasUnitPrice(new UnitPrice(new BigDecimal(16.99)))
            .hasQuantity(new Quantity(3));

        PizzasAssertions.assertThat(pizza).withStuffedCrust(false);
    }
}
