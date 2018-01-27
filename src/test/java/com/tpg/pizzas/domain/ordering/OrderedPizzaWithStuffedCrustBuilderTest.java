package com.tpg.pizzas.domain.ordering;

import com.tpg.pizzas.domain.AmericanHot;
import com.tpg.pizzas.domain.InvalidPizzaException;
import com.tpg.pizzas.domain.Pizzas;
import com.tpg.pizzas.domain.PizzasAssertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.tpg.pizzas.domain.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pizzas.domain.ordering.OrderingAssertions.assertThat;

public class OrderedPizzaWithStuffedCrustBuilderTest {

    private OrderedPizzaWithStuffedCrust.Builder builder;

    @Before
    public void setUp() {

        builder = new OrderedPizzaWithStuffedCrust.Builder();
    }

    @Test
    public void build() throws InvalidPizzaException {

        AmericanHot pizza = (AmericanHot) Pizzas.americanHot().crustiness(DEEP_CRUST).withStuffedCrust(true).build();

        BigDecimal pizzaUnitPrice = new BigDecimal(14.99);

        BigDecimal unitPrice = new BigDecimal(2.50);

        OrderedPizzaWithStuffedCrust actual = builder
            .pizzaUnitPrice(pizzaUnitPrice)
            .unitPrice(unitPrice)
            .quantity(2)
            .pizza(pizza)
            .build();

        assertThat(actual)
            .hasOrderedPizza(new OrderedPizza.Builder().pizza(pizza).unitPrice(pizzaUnitPrice).quantity(2).build())
            .hasUnitPrice(new UnitPrice(unitPrice))
            .hasQuantity(new Quantity(2))
            .hasCost(new Cost(new BigDecimal(2 * (14.99 + 2.50))));

        PizzasAssertions.assertThat(pizza).withStuffedCrust(true);
    }
}
