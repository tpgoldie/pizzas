package com.tpg.pjs.pizzas;

import static com.tpg.pjs.pizzas.PizzaErrors.MISSING_CRUSTINESS;

public class MissingCrustinessException extends InvalidPizzaException {

    public MissingCrustinessException() {

        super(MISSING_CRUSTINESS);
    }
}
