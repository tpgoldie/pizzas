package com.tpg.pjs.ordering;

import com.tpg.pjs.pizzas.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.tpg.pjs.pizzas.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pjs.ordering.OrderingAssertions.assertThat;

public class AmericanHotOrderedBuildingTest {

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
            .hasQuantity(new Quantity(3))
            .hasCost(new Cost(new BigDecimal(3 * 16.99)));

        PizzasAssertions.assertThat(pizza).withStuffedCrust(false);
    }
}
