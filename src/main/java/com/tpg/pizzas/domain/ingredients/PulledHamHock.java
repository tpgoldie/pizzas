package com.tpg.pizzas.domain.ingredients;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class PulledHamHock extends Ingredient {

    private static final PulledHamHock INSTANCE = new PulledHamHock();

    public static Ingredient ingredient() { return INSTANCE; }

    private PulledHamHock() {
        super("pulled ham hock");
    }
}
