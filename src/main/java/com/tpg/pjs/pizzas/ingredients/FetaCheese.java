package com.tpg.pjs.pizzas.ingredients;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class FetaCheese extends Ingredient {

    private static final FetaCheese INSTANCE = new FetaCheese();

    public static Ingredient ingredient() { return INSTANCE; }

    private FetaCheese() {
        super("feta cheese");
    }
}
