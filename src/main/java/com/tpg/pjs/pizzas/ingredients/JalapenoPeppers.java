package com.tpg.pjs.pizzas.ingredients;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class JalapenoPeppers extends Ingredient {

    private static final JalapenoPeppers INSTANCE = new JalapenoPeppers();

    public static Ingredient ingredient() { return INSTANCE; }

    private JalapenoPeppers() {
        super("jalapeno peppers");
    }
}
