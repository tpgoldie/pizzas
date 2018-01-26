package com.tpg.pizzas.domain.toppings;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class JalapenoPeppers extends Topping {

    private static final JalapenoPeppers INSTANCE = new JalapenoPeppers();

    public static Topping topping() { return INSTANCE; }

    private JalapenoPeppers() {
        super("jalapeno peppers");
    }
}
