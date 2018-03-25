package com.tpg.pjs.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpg.pjs.config.GuestSecurityConfig;
import com.tpg.pjs.config.MethodSecurityConfig;
import com.tpg.pjs.config.MultipleEntryPointsSecurityConfig;
import com.tpg.pjs.ordering.OrderDetailsRequestFixture;
import com.tpg.pjs.services.OrderDetailsRequest;
import com.tpg.pjs.services.OrderDetailsResponse;
import com.tpg.pjs.services.OrderingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.tpg.pjs.ordering.Order.Status.PENDING;
import static com.tpg.pjs.pizzas.Pizza.Crustiness.ORIGINAL;
import static com.tpg.pjs.pizzas.Pizza.Size.LARGE;
import static com.tpg.pjs.pizzas.PizzaCode.CHICKEN_CLUB_CODE;
import static com.tpg.pjs.rest.PlaceNewOrder.given;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderingController.class)
@ActiveProfiles(profiles = { "dev" })
@ContextConfiguration(classes = { MultipleEntryPointsSecurityConfig.class, GuestSecurityConfig.class, MethodSecurityConfig.class })
public class OrderingControllerTest implements OrderDetailsRequestFixture {

    @MockBean
    private OrderingService orderingService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsService userDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    private UsernamePasswordAuthenticationToken authentication;

    @Before
    public void setUp() {

        authentication = new UsernamePasswordAuthenticationToken("pjs", "pjs",
                singletonList(new SimpleGrantedAuthority("PJS_GUEST")));
    }

    @Test
    @WithMockUser(username = "pjs", password = "pjs", roles = {"PJS_GUEST"})
    public void placeOrder_orderRequest_shouldHandlePlacedOrder() throws Exception {

        OrderDetailsRequest orderRequest = orderAPizza("pjs", generateString(5),
                "12/03/2017 20:19", CHICKEN_CLUB_CODE, LARGE, ORIGINAL, 15.99, 2, PENDING);

        OrderDetailsResponse orderResponse = OrderDetailsResponse.builder()
                .userId(orderRequest.getUserId())
                .orderId(generateString(5))
                .orderDetailsRequest(orderRequest)
                .orderStatus(PENDING)
                .build();

        given(mockMvc)
            .orderDetailsRequest(orderRequest)
            .orderDetailsResponse(orderResponse)
            .anObjectMapper(objectMapper)
            .orderingService(orderingService)
            .userDetailsService(userDetailsService)
            .authentication(authentication)
        .when()
            .postingNewOrder()
        .then()
            .statusIs(OK)
            .orderResponseIs(orderResponse);

        verify(orderingService).placeOrder(orderRequest);
    }
}
