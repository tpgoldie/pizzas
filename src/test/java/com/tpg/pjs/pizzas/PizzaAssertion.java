package com.tpg.pjs.pizzas;

import com.tpg.pjs.pizzas.Pizza.Crustiness;
import com.tpg.pjs.pizzas.Pizza.Size;
import com.tpg.pjs.pizzas.Pizza.Type;
import com.tpg.pjs.pizzas.ingredients.Ingredient;
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

    public PizzaAssertion hasItemCode(String value) {

        assertThat(actual.getItemCode()).isEqualTo(value);

        return this;
    }

    PizzaAssertion hasDescription(String value) {

        assertThat(actual.getDescription()).isEqualTo(value);

        return this;
    }

    PizzaAssertion hasType(Type value) {

        assertThat(actual.getType()).isEqualTo(value);

        return this;
    }

    public PizzaAssertion hasItemTypeCode(String value) {

        assertThat(actual.getItemTypeCode()).isEqualTo(value);

        return this;
    }

    public PizzaAssertion hasName(String value) {

        assertThat(actual.getName()).isEqualTo(value);

        return this;
    }

    public PizzaAssertion hasSize(Size value) {

        assertThat(actual.getSize()).isEqualTo(value);

        return this;
    }

    public PizzaAssertion hasCrustiness(Crustiness value) {

        assertThat(actual.getCrustiness()).isEqualTo(value);

        return this;
    }

    public PizzaAssertion withStuffedCrust(boolean value) {

        assertThat(actual.withStuffedCrust()).isEqualTo(value);

        return this;
    }

    public PizzaAssertion hasIngredients(Ingredient... ingredients) {

        Set<Ingredient> expectedValues = Arrays.stream(ingredients).collect(toSet());

        Set<Ingredient> actualValues = new HashSet<>(actual.getIngredients());

        assertThat(actualValues).isEqualTo(expectedValues);

        return this;
    }
}
