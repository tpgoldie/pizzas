package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.Pizza.Crustiness;
import com.tpg.pizzas.domain.Pizza.Size;
import com.tpg.pizzas.domain.Pizza.Type;
import com.tpg.pizzas.domain.ingredients.Ingredient;
import org.assertj.core.api.AbstractAssert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class PizzaAssertion<U extends Pizza> extends AbstractAssert<PizzaAssertion<U>, U> {

    PizzaAssertion(Pizza actual) {

        super((U) actual, PizzaAssertion.class);
    }

    PizzaAssertion hasDescription(String value) {

        assertThat(actual.getDescription()).isEqualTo(value);

        return this;
    }

    PizzaAssertion hasType(Type value) {

        assertThat(actual.getType()).isEqualTo(value);

        return this;
    }

    public PizzaAssertion hasName(String value) {

        assertThat(actual.getName()).isEqualTo(value);

        return this;
    }

    PizzaAssertion hasSize(Size value) {

        assertThat(actual.getSize()).isEqualTo(value);

        return this;
    }

    PizzaAssertion hasCrustiness(Crustiness value) {

        assertThat(actual.getCrustiness()).isEqualTo(value);

        return this;
    }

    public PizzaAssertion withStuffedCrust(boolean value) {

        assertThat(actual.withStuffedCrust()).isEqualTo(value);

        return this;
    }

    PizzaAssertion hasIngredients(Ingredient... ingredients) {

        Set<Ingredient> expectedValues = Arrays.stream(ingredients).collect(toSet());

        Set<Ingredient> actualValues = new HashSet<>(actual.getIngredients());

        assertThat(actualValues).isEqualTo(expectedValues);

        return this;
    }
}
