package com.tpg.pjs.ordering;

import com.tpg.pjs.pizzas.InvalidPizzaException;
import com.tpg.pjs.pizzas.Pizza;
import com.tpg.pjs.pizzas.Pizza.Crustiness;

import java.math.BigDecimal;

import static com.tpg.pjs.pizzas.Pizzas.papasFavourite;
import static com.tpg.pjs.pizzas.Pizzas.theGreek;

public interface OrderedPizzaFixture {

    default OrderItem theGreekOrdered(double price, int quantity) throws InvalidPizzaException {

        Pizza pizza = theGreek().build();

        return new OrderedPizza.Builder()
            .pizza(pizza)
            .quantity(quantity)
            .unitPrice(new BigDecimal(price))
            .build();
    }

    default OrderItem papasFavouriteOrdered(Pizza.Size size, Crustiness crustiness, double price, int quantity) throws InvalidPizzaException {

        Pizza pizza = papasFavourite().size(size).crustiness(crustiness).build();

        return new OrderedPizza.Builder()
            .pizza(pizza)
            .quantity(quantity)
            .unitPrice(new BigDecimal(price))
            .build();
    }
}
