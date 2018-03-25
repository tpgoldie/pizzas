package com.tpg.pjs.persistence.repositories;

import com.tpg.pjs.persistence.entities.OrderEntity;
import com.tpg.pjs.persistence.entities.OrderItemEntity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static com.tpg.pjs.ordering.OrderedItemType.PIZZA;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.THIN_CRUST;
import static com.tpg.pjs.pizzas.Pizza.Size.LARGE;
import static com.tpg.pjs.pizzas.PizzaCode.CHICKEN_CLUB_CODE;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class OrdersLifecycleRepositoryTest extends OrdersRepositoryTest {

    @Test
    public void save() {

        lifecycleRepository.save(order);
    }

    @Autowired
    private OrdersLifecycleRepository lifecycleRepository;

    private OrderEntity order;
}
