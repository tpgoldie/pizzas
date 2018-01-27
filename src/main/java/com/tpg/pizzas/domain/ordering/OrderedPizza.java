package com.tpg.pizzas.domain.ordering;


import com.tpg.pizzas.domain.Pizza;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class OrderedPizza extends OrderItem {

    private Pizza pizza;

    OrderedPizza(Builder builder) {

        super(builder);

        pizza = builder.pizza;
    }

    @Override
    public String toString() {

        return String.format("%s @ %s", pizza, super.toString());
    }

    public static class Builder extends OrderItem.Builder<Builder> {

        private Pizza pizza;

        public Builder pizza(Pizza value) {

            this.pizza = value;

            return self();
        }

        @Override
        public OrderedPizza build() {

            return new OrderedPizza(this);
        }

        @Override
        protected Builder self() {

            return this;
        }
    }
}
