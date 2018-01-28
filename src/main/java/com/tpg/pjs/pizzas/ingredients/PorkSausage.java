package com.tpg.pjs.pizzas.ingredients;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class PorkSausage extends Ingredient {

    private static final PorkSausage INSTANCE = new PorkSausage();

    public static Ingredient ingredient() { return INSTANCE; }

    private PorkSausage() {
        super("pork sausage");
    }
}
