package com.tpg.pjs.ordering;

import com.tpg.pjs.pizzas.InvalidPizzaException;
import com.tpg.pjs.pizzas.Pizza;

import java.math.BigDecimal;
import java.util.Optional;

import static com.tpg.pjs.ordering.OrderedItemType.PIZZA;

public final class OrderedItemBuilding {

    public static OrderedItemBuilding when(String itemTypeCode) {

        return new OrderedItemBuilding().itemTypeCode(itemTypeCode);
    }

    private String itemTypeCode;

    private boolean buildPizza;

    private OrderedItemBuilding() {}

    private OrderedItemBuilding itemTypeCode(String itemTypeCode) {

        this.itemTypeCode = itemTypeCode;

        return this;
    }

    public OrderedItemBuilding matchesPizza() {

        Optional<OrderedItemType> found = OrderedItemType.byCode(itemTypeCode);

        buildPizza = found.isPresent() && found.get() == PIZZA;

        return this;
    }

    public Optional<OrderItem> buildOrderedPizza(Pizza.Builder pizzaBuilder, OrderItemDetails orderItemDetails) {

        if (!buildPizza) { return Optional.empty(); }

        return build(pizzaBuilder, orderItemDetails);
    }

    private Optional<OrderItem> build(Pizza.Builder builder, OrderItemDetails itemDetails){

        try {

            Pizza pizza = builder
                    .size(Pizza.Size.valueOf(itemDetails.getSize()))
                    .crustiness(Pizza.Crustiness.valueOf(itemDetails.getCrustiness()))
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

}
