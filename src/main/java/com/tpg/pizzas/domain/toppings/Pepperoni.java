package com.tpg.pizzas.domain.toppings;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class Pepperoni extends Topping {

    private static final Pepperoni INSTANCE = new Pepperoni();

    public static Topping topping() { return INSTANCE; }

    private Pepperoni() {
        super("pepperoni");
    }
}
