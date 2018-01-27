package com.tpg.pizzas.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.tpg.pizzas.domain.Pizza.Size.MEDIUM;

public abstract class PizzaBuilderTest {

    static final String DESCRIPTION = "A lovely pizza";

    protected Pizza.Builder builder;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void handleMissingCrustiness() throws InvalidPizzaException {

        expectedException.expect(InvalidPizzaException.class);

        expectedException.expectMessage("missing crustiness");

        builder.size(MEDIUM)
                .description(DESCRIPTION)
                .build();
    }
}
