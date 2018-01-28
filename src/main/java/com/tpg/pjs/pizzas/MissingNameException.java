package com.tpg.pjs.pizzas;

import static com.tpg.pjs.pizzas.PizzaErrors.MISSING_CRUSTINESS;

public class MissingNameException extends InvalidPizzaException {

    public MissingNameException() {

        super(MISSING_CRUSTINESS);
    }
}
