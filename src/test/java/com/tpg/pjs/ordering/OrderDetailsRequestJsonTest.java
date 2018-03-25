package com.tpg.pjs.ordering;

import com.tpg.pjs.pizzas.InvalidPizzaException;
import com.tpg.pjs.services.OrderDetailsRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.tpg.pjs.ordering.Order.Status.PENDING;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.THIN_CRUST;
import static com.tpg.pjs.pizzas.Pizza.Size.MEDIUM;
import static com.tpg.pjs.pizzas.PizzaCode.AMERICAN_HOT_CODE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class OrderDetailsRequestJsonTest implements OrderDetailsRequestFixture {

    @Autowired
    private JacksonTester<OrderDetailsRequest> tester;

    private OrderDetailsRequest request;

    @Before
    public void setUp() throws InvalidPizzaException {

        request = orderAPizza("jdoe", "12/06/2016 12:31",
                AMERICAN_HOT_CODE, MEDIUM, THIN_CRUST, 16.99, 2, PENDING);
    }

    @Test
    public void serializeJson() throws Exception {

        JsonContent<OrderDetailsRequest> actual = tester.write(request);

        assertThat(actual)
                .extractingJsonPathStringValue("@.userId")
                    .isEqualTo(request.getUserId());

        assertThat(actual)
            .extractingJsonPathStringValue("@.status")
                .isEqualTo(PENDING.name());

        assertThat(actual)
                .extractingJsonPathStringValue("@.dateOrdered")
                .isEqualTo("12/06/2016 12:31");

        assertThat(actual)
            .extractingJsonPathArrayValue("@.orderedItems")
                .isNotEmpty();
    }

    @Test
    public void deserializeJson() throws Exception {

        OrderDetailsRequest actual = tester.read(new ClassPathResource("json/orderDetailsRequest.json")).getObject();

        OrderingAssertions.assertThat(actual)
                .hasUserId(request.getUserId()).hasOrderItems(request.getOrderedItems());
    }
}
