package com.tpg.pjs.ordering;

import com.tpg.pjs.pizzas.InvalidPizzaException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class OrderDetailsRequestJsonTest implements OrderDetailsRequestFixture {

    @Autowired
    private JacksonTester<OrderDetailsRequest> tester;

    private OrderDetailsRequest request;

    @Before
    public void setUp() throws InvalidPizzaException {

        request = newOrderDetailsRequest("jdoe");
    }

    @Test
    public void serializeJson() throws Exception {

        assertThat(tester.write(request))
                .extractingJsonPathStringValue("@.userId")
                    .isEqualTo(request.getUserId());

        List<OrderItemDetails> items = request.getOrderItems();

        OrderItemDetails[] expected = request.getOrderItems().toArray(new OrderItemDetails[items.size()]);

        assertThat(tester.write(request))
            .extractingJsonPathArrayValue("@.orderItems")
                .isNotEmpty();
    }

    @Test
    public void deserializeJson() throws Exception {

        OrderDetailsRequest actual = tester.read(new ClassPathResource("json/orderDetailsRequest.json")).getObject();

        OrderingAssertions.assertThat(actual).hasUserId(request.getUserId()).hasOrderItems(request.getOrderItems());
    }
}
