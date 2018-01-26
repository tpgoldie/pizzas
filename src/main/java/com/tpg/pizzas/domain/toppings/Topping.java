package com.tpg.pizzas.domain.toppings;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public abstract class Topping {

    @Getter
    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
