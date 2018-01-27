package com.tpg.pizzas.domain;

import static com.tpg.pizzas.domain.PizzaErrors.MISSING_CRUSTINESS;

public class MissingNameException extends InvalidPizzaException {

    public MissingNameException() {

        super(MISSING_CRUSTINESS);
    }
}
