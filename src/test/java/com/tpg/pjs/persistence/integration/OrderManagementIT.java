package com.tpg.pjs.persistence.integration;

import com.tpg.pjs.PjsApplication;
import com.tpg.pjs.StringGeneration;
import com.tpg.pjs.config.PersistenceConfig;
import com.tpg.pjs.persistence.entities.OrderEntity;
import com.tpg.pjs.persistence.entities.OrderEntityFixture;
import com.tpg.pjs.persistence.entities.OrderItemEntity;
import com.tpg.pjs.persistence.entities.OrderItemEntityFixture;
import com.tpg.pjs.persistence.repositories.OrdersLifecycleRepository;
import com.tpg.pjs.persistence.repositories.OrdersQueriesRepository;
import com.tpg.pjs.pizzas.Pizza;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static com.tpg.pjs.ordering.OrderedItemType.PIZZA;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.ORIGINAL;
import static com.tpg.pjs.pizzas.PizzaCode.AMERICAN_HOT_CODE;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {PjsApplication.class, Persistence.class})
@TestPropertySource(properties = {
    "spring.datasource.driver-class-name=org.postgresql.Driver",
    "spring.datasource.url= jdbc:postgresql://localhost:5432/pjs",
    "spring.datasource.platform=postgresql",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.datasource.username=pjs",
    "spring.datasource.password=pjs"
})
public class OrderManagementIT implements StringGeneration, OrderEntityFixture, OrderItemEntityFixture {

    @Autowired
    private OrdersQueriesRepository ordersQueriesRepository;

    @Autowired
    private OrdersLifecycleRepository ordersLifecycleRepository;

    @Test
    public void saveOrder() {
        OrderItemEntity orderItem = newOrderItemEntity(PIZZA.getCode(), AMERICAN_HOT_CODE.getValue(), Pizza.Size.MEDIUM.name(),
                ORIGINAL.name(), new BigDecimal(15.95), 2);

        OrderEntity order = newOrderEntity("jdoe", generateString(5), ZonedDateTime.now(),
                singletonList(orderItem));

        ordersLifecycleRepository.save(order);

        List<OrderEntity> actual = ordersQueriesRepository.findByUserId("jdoe");

        assertEquals(1, actual.size());

        assertThat(actual.get(0).getUserId()).isEqualTo("jdoe");
    }
}
