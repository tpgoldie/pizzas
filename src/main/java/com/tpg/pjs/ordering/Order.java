package com.tpg.pjs.ordering;


import com.tpg.pjs.pizzas.InvalidPizzaException;
import com.tpg.pjs.pizzas.Pizza;
import com.tpg.pjs.pizzas.Pizza.Crustiness;
import com.tpg.pjs.services.DateHandling;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Getter
@EqualsAndHashCode
public class Order implements DateHandling {

    public enum Status { PENDING, COMPLETED, CANCELLED }

    private String userId;

    private ZonedDateTime dateOrdered;

    private List<OrderItem> orderedItems;

    Order(Builder builder) {

        userId = builder.userId;

        dateOrdered = toZonedDateTime(builder.dateOrdered);

        orderedItems = builder.orderedItems;
    }

    public static class Builder {

        private String userId;

        private String dateOrdered;

        private List<OrderItem> orderedItems;

        private final OrderedPizzaBuilderSelector orderedPizzaBuilderSelector = new OrderedPizzaBuilderSelector();

        public Builder userId(String value) {

            this.userId = value;

            return this;
        }

        public Builder dateOrdered(String value) {

            this.dateOrdered = value;

            return this;
        }

        public Builder itemsOrdered(List<OrderItemDetails> value) {

            this.orderedItems = value.stream().map(this::toOrderItem)
                    .filter(Optional::isPresent).map(Optional::get)
                        .collect(toList());

            return this;
        }

        private Optional<OrderItem> toOrderItem(OrderItemDetails itemDetails) {

            OrderedItemType orderedItemType = OrderedItemType.valueOf(itemDetails.getItemTypeCode());

            switch (orderedItemType) {
                case PIZZA:
                    return orderedPizzaBuilderSelector.select(itemDetails.getItemCode())
                            .flatMap(pb -> buildOrderedPizza(pb, itemDetails));
            }

            return Optional.empty();
        }

        private Optional<OrderItem> buildOrderedPizza(Pizza.Builder builder, OrderItemDetails itemDetails){
            try {

                Pizza pizza = builder
                        .size(Pizza.Size.valueOf(itemDetails.getSize()))
                        .crustiness(Crustiness.valueOf(itemDetails.getCrustiness()))
                        .build();

                OrderedPizza orderedPizza = new OrderedPizza.Builder()
                        .pizza(pizza)
                        .unitPrice(new BigDecimal(itemDetails.getPrice()))
                        .quantity(itemDetails.getQuantity())
                        .build();

                return Optional.of(orderedPizza);

            }
            catch (InvalidPizzaException e) {
                return Optional.empty();
            }
        }

        public Order build() {

            return new Order(this);
        }
    }
}
