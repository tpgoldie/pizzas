package com.tpg.pjs.pizzas.ingredients;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public abstract class Ingredient {

    @Getter
    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
