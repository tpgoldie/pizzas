package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.toppings.Cheese;
import com.tpg.pizzas.domain.toppings.JalapenoPeppers;
import com.tpg.pizzas.domain.toppings.Pepperoni;
import com.tpg.pizzas.domain.toppings.Topping;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

import static java.util.Arrays.asList;

@Getter
@EqualsAndHashCode
public abstract class Pizza {

    public enum Size { SMALL, MEDIUM, LARGE, X_LARGE }

    public enum Type { MEATS, VEGETARIAN }

    private Type type;

    private String description;

    private Size size;

    private List<Topping> toppings;

    private boolean stuffedCrust;

    Pizza(Builder<?> builder) {

        type = builder.type;
        size = builder.size;
        description = builder.description;
        toppings = builder.toppings;
        stuffedCrust = builder.withStuffedCrust;
    }

    abstract static class Builder<T extends Builder<T>> {

        private List<Topping> toppings = asList(Cheese.topping(), Pepperoni.topping(), JalapenoPeppers.topping());

        private String description;

        private Type type;

        private Size size;

        private boolean withStuffedCrust;

        public T size(Size value) {

            this.size = value;

            return self();
        }

        public T type(Type value) {

            this.type = value;

            return self();
        }

        public T description(String value) {

            this.description = value;

            return self();
        }

        public T withStuffedCrust(boolean value) {

            this.withStuffedCrust = value;

            return self();
        }

        abstract Pizza build();

        protected abstract T self();
    }
}
