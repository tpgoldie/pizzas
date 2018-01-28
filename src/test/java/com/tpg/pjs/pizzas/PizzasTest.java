package com.tpg.pjs.pizzas;

import org.junit.Test;

import static com.tpg.pjs.pizzas.Pizza.Crustiness.DEEP_CRUST;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.THIN_CRUST;
import static com.tpg.pjs.pizzas.Pizzas.*;
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

    @Test
    public void buildChickenClub() throws InvalidPizzaException {

        ChickenClub actual = (ChickenClub) chickenClub().build();

        assertThat(actual).isNotNull();
    }
    @Test
    public void buildPapasFavourite() throws InvalidPizzaException {

        PapasFavourite actual = (PapasFavourite) papasFavourite().build();

        assertThat(actual).isNotNull();
    }
}
