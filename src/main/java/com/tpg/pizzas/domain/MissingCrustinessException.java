package com.tpg.pizzas.domain;

import static com.tpg.pizzas.domain.PizzaErrors.MISSING_CRUSTINESS;

public class MissingCrustinessException extends InvalidPizzaException {

    public MissingCrustinessException() {

        super(MISSING_CRUSTINESS);
    }
}
