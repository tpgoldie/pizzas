package com.tpg.pjs.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpg.pjs.config.GuestSecurityConfig;
import com.tpg.pjs.config.MethodSecurityConfig;
import com.tpg.pjs.config.MultipleEntryPointsSecurityConfig;
import com.tpg.pjs.config.WebAppConfig;
import com.tpg.pjs.ordering.OrderDetailsRequestFixture;
import com.tpg.pjs.services.OrderDetailsRequest;
import com.tpg.pjs.services.OrderDetailsResponse;
import com.tpg.pjs.services.OrderingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.tpg.pjs.ordering.Order.Status.PENDING;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.ORIGINAL;
import static com.tpg.pjs.pizzas.Pizza.Size.LARGE;
import static com.tpg.pjs.pizzas.PizzaCode.CHICKEN_CLUB_CODE;
import static com.tpg.pjs.rest.PlaceNewOrder.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(OrderingController.class)
@ActiveProfiles(profiles = { "dev" })
@ContextConfiguration(classes = { MultipleEntryPointsSecurityConfig.class, GuestSecurityConfig.class, MethodSecurityConfig.class })
public class OrderingControllerTest implements OrderDetailsRequestFixture {

    @MockBean
    private OrderingService orderingService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username="pjs", password = "pjs", roles = {"PJS"})
    public void placeOrder_orderRequest_shouldHandlePlacedOrder() throws Exception {

        OrderDetailsRequest orderRequest = orderAPizza("pjs", "12/03/2017 20:19",
                CHICKEN_CLUB_CODE, LARGE, ORIGINAL, 15.99, 2);

        OrderDetailsResponse orderDetailsResponse = OrderDetailsResponse.builder()
                .userId("jdoe")
                .orderId(generateString(5))
                .orderDetailsRequest(orderRequest)
                .orderStatus(PENDING).build();

        given(mockMvc)
            .anOrderDetailsRequest(orderRequest)
            .anObjectMapper(objectMapper)
        .when()
            .postingNewOrder()
        .then()
            .statusIs(OK)
            .orderResponseIs(orderDetailsResponse);

        verify(orderingService).placeOrder(orderRequest);
    }
}
