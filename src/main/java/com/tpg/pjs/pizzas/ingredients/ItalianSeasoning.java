package com.tpg.pjs.pizzas.ingredients;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class ItalianSeasoning extends Ingredient {

    private static final ItalianSeasoning INSTANCE = new ItalianSeasoning();

    public static Ingredient ingredient() { return INSTANCE; }

    private ItalianSeasoning() {
        super("italian seasoning");
    }
}
