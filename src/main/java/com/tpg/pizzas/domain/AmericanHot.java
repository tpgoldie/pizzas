package com.tpg.pizzas.domain;

final class AmericanHot extends Pizza {

    private AmericanHot(Builder builder) {
        super(builder);
    }

    public static class Builder extends Pizza.Builder<Builder> {

        @Override
        Pizza build() {
            return new AmericanHot(this);
        }

        @Override
        protected Builder self() {

            return this;
        }
    }
}
