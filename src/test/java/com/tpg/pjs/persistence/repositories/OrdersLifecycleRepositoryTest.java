package com.tpg.pjs.persistence.repositories;

import com.tpg.pjs.persistence.entities.OrderEntity;
import com.tpg.pjs.persistence.entities.OrderEntityFixture;
import com.tpg.pjs.persistence.entities.OrderItemEntity;
import com.tpg.pjs.persistence.entities.OrderItemEntityFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static com.tpg.pjs.ordering.OrderedItemType.PIZZA;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.THIN_CRUST;
import static com.tpg.pjs.pizzas.Pizza.Size.LARGE;
import static com.tpg.pjs.pizzas.PizzaCode.CHICKEN_CLUB_CODE;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles({"dev"})
public class OrdersLifecycleRepositoryTest implements OrderEntityFixture, OrderItemEntityFixture {

    @Test
    public void save() {

        OrderEntity entity = newOrderEntity("pjs", "abc-123", ZonedDateTime.now(), emptyList());

        List<OrderItemEntity> items = singletonList(newOrderItemEntity(entity, PIZZA.getCode(),
                CHICKEN_CLUB_CODE.getValue(), LARGE.name(), THIN_CRUST.name(), new BigDecimal(15.95),
                2));

        entity.setOrderItems(items);

        lifecycleRepository.save(entity);
    }

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrdersLifecycleRepository lifecycleRepository;
}
