package com.tpg.pizzas.domain;

import org.junit.Test;

import static com.tpg.pizzas.domain.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pizzas.domain.Pizza.Crustiness.ORIGINAL;
import static com.tpg.pizzas.domain.Pizza.Crustiness.THIN_CRUST;
import static com.tpg.pizzas.domain.Pizzas.americanHot;
import static com.tpg.pizzas.domain.Pizzas.premiumHawaiian;
import static com.tpg.pizzas.domain.Pizzas.theGreek;
import static org.assertj.core.api.Assertions.assertThat;

public class PizzasTest {

    @Test
    public void buildAmericanHot() throws InvalidPizzaException {

        AmericanHot actual = (AmericanHot) americanHot().crustiness(DEEP_CRUST).build();

        assertThat(actual).isNotNull();
    }

    @Test
    public void buildTheGreek() throws InvalidPizzaException {

        TheGreek actual = (TheGreek) theGreek().crustiness(THIN_CRUST).build();

        assertThat(actual).isNotNull();
    }

    @Test
    public void buildPremiumHawaiian() throws InvalidPizzaException {

        PremiumHawaiian actual = (PremiumHawaiian) premiumHawaiian().build();

        assertThat(actual).isNotNull();
    }
}
