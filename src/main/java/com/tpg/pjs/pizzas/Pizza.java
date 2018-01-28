package com.tpg.pjs.pizzas;

import com.tpg.pjs.pizzas.ingredients.Ingredient;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

import static com.tpg.pjs.pizzas.Pizza.Crustiness.ORIGINAL;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Getter
@EqualsAndHashCode
public abstract class Pizza implements WithStuffedCrust {

    public enum Size { SMALL, MEDIUM, LARGE, X_LARGE }

    public enum Type { MEATS, VEGETARIAN }

    public enum Crustiness { THIN_CRUST, ORIGINAL, DEEP_CRUST }

    private Type type;

    private String name;

    private String code;

    private String description;

    private Size size;

    private Crustiness crustiness;

    private List<Ingredient> ingredients;

    private boolean stuffedCrust;

    Pizza(Builder<?> builder, Type type, Ingredient... ingredients) {

        assert ingredients.length > 0;

        this.name = builder.name;

        this.code = builder.code;

        this.type = type;

        this.crustiness = builder.crustiness;

        this.ingredients = stream(ingredients).collect(toList());

        size = builder.size;
        description = builder.description;
        stuffedCrust = builder.withStuffedCrust;
    }

    public boolean withStuffedCrust() {

        return stuffedCrust;
    }

    @Override
    public String toString() {

        return name;
    }

    public abstract static class Builder<T extends Builder<T>> {

        private String name;

        private String code;

        private String description;

        private Size size;

        private Crustiness crustiness = ORIGINAL;

        private boolean withStuffedCrust;

        public T code(String value) {

            this.code = value;

            return self();
        }

        public T name(String value) {

            this.name = value;

            return self();
        }

        public T size(Size value) {

            this.size = value;

            return self();
        }

        public T description(String value) {

            this.description = value;

            return self();
        }

        public T crustiness(Crustiness value) {

            this.crustiness = value;

            return self();
        }

        public T withStuffedCrust(boolean value) {

            this.withStuffedCrust = value;

            return self();
        }

        public abstract Pizza build() throws InvalidPizzaException;

        protected abstract T self();

        void validateCrustiness() throws MissingCrustinessException {

            if (crustiness == null) { throw new MissingCrustinessException(); }
        }
    }
}
