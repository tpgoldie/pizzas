package com.tpg.pizzas.domain.ingredients;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class Bacon extends Ingredient {

    private static final Bacon INSTANCE = new Bacon();

    public static Ingredient ingredient() { return INSTANCE; }

    private Bacon() {
        super("bacon");
    }
}
