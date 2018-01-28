package com.tpg.pjs.pizzas.ingredients;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class ItalianSixCheeseBlend extends Ingredient {

    private static final ItalianSixCheeseBlend INSTANCE = new ItalianSixCheeseBlend();

    public static Ingredient ingredient() { return INSTANCE; }

    private ItalianSixCheeseBlend() {
        super("italian six cheese blend");
    }
}
