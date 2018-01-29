package com.tpg.pjs.ordering;

import com.tpg.pjs.pizzas.InvalidPizzaException;
import com.tpg.pjs.pizzas.Pizza;

import java.math.BigDecimal;

import static com.tpg.pjs.pizzas.Pizzas.theGreek;

public interface OrderedPizzaFixture {

    default OrderItem orderedPizza() throws InvalidPizzaException {

        Pizza pizza = theGreek().build();

        return new OrderedPizza.Builder()
            .pizza(pizza).quantity(2)
            .unitPrice(new BigDecimal(14.95))
            .build();
    }
}
