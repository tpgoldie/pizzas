package com.tpg.pjs.ordering;


import com.tpg.pjs.pizzas.Pizza;
import com.tpg.pjs.services.DateHandling;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static com.tpg.pjs.ordering.OrderedItemBuilding.when;
import static java.util.stream.Collectors.toList;

@Getter
@EqualsAndHashCode
public class Order implements DateHandling {

    public enum Status { PENDING, ACCEPTED, COMPLETED, CANCELLED }

    private String userId;

    private String sessionId;

    private ZonedDateTime dateOrdered;

    private List<OrderItem> orderedItems;

    Order(Builder builder) {

        userId = builder.userId;

        sessionId = builder.sessionId;

        dateOrdered = toZonedDateTime(builder.dateOrdered);

        orderedItems = builder.orderedItems;
    }

    public static class Builder {

        private String userId;

        private String sessionId;

        private String dateOrdered;

        private List<OrderItem> orderedItems;

        private final OrderedPizzaBuilderSelector orderedPizzaBuilderSelector = new OrderedPizzaBuilderSelector();

        public Builder userId(String value) {

            this.userId = value;

            return this;
        }

        public Builder sessionId(String value) {

            this.sessionId = value;

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

            return OrderedItemType.byCode(itemDetails.getItemTypeCode())
                .flatMap(oit -> lookupBuilder(oit, itemDetails));
        }

        private Optional<OrderItem> lookupBuilder(OrderedItemType orderedItemType, OrderItemDetails itemDetails) {

            switch (orderedItemType) {
                case PIZZA:
                    Optional<Pizza.Builder> pizzaBuilder = orderedPizzaBuilderSelector.select(itemDetails.getItemCode());
                    return pizzaBuilder.flatMap(pb -> when(itemDetails.getItemTypeCode()).matchesPizza()
                            .buildOrderedPizza(pb, itemDetails));

            }

            return Optional.empty();
        }

        public Order build() {

            return new Order(this);
        }
    }
}
