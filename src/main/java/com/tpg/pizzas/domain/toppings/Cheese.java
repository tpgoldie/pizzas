package com.tpg.pizzas.domain.toppings;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class Cheese extends Topping {

    private static final Cheese INSTANCE = new Cheese();

    public static Topping topping() { return INSTANCE; }

    private Cheese() {
        super("cheese");
    }
}
