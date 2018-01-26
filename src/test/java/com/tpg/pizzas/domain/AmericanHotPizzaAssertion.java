package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.Pizza.Size;
import com.tpg.pizzas.domain.Pizza.Type;
import com.tpg.pizzas.domain.toppings.Topping;
import org.assertj.core.api.AbstractAssert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

public class AmericanHotPizzaAssertion extends AbstractAssert<AmericanHotPizzaAssertion, AmericanHot> {

    public AmericanHotPizzaAssertion(AmericanHot actual) {

        super(actual, AmericanHotPizzaAssertion.class);
    }

    public AmericanHotPizzaAssertion hasDescription(String value) {

        assertThat(actual.getDescription()).isEqualTo(value);

        return this;
    }

    public AmericanHotPizzaAssertion hasSize(Size value) {

        assertThat(actual.getSize()).isEqualTo(value);

        return this;
    }

    public AmericanHotPizzaAssertion hasType(Type value) {

        assertThat(actual.getType()).isEqualTo(value);

        return this;
    }

    public AmericanHotPizzaAssertion withStuffedCrust(boolean value) {

        assertThat(actual.isStuffedCrust()).isEqualTo(value);

        return this;
    }

    public AmericanHotPizzaAssertion hasToppings(Topping... toppings) {

        Set<Topping> expectedValues = Arrays.stream(toppings).collect(toSet());

        Set<Topping> actualValues = new HashSet<>(actual.getToppings());

        assertThat(actualValues).isEqualTo(expectedValues);

        return this;
    }
}
