package com.tpg.pjs.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.services.OrderDetailsRequest;
import com.tpg.pjs.services.OrderDetailsResponse;
import com.tpg.pjs.services.OrderingService;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static com.tpg.pjs.ordering.Order.Status.PENDING;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlaceNewOrder {

    private UsernamePasswordAuthenticationToken authentication;
    private OrderingService orderingService;
    private OrderDetailsResponse orderResponse;

    static PlaceNewOrder given(MockMvc mockMvc) {

        return new PlaceNewOrder(mockMvc);
    }

    private final MockMvc mockMvc;

    private OrderDetailsRequest orderDetailsRequest;

    private ResultActions result;

    private ObjectMapper objectMapper;

    private UserDetailsService userDetailsService;

    private PlaceNewOrder(MockMvc mockMvc) {

        this.mockMvc = mockMvc;
    }

    PlaceNewOrder orderDetailsRequest(OrderDetailsRequest orderRequest) {

        this.orderDetailsRequest = orderRequest;

        return this;
    }

    PlaceNewOrder anObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

        return this;
    }

    PlaceNewOrder when() { return this; }

    PlaceNewOrder postingNewOrder() throws Exception {

        String output = objectMapper.writeValueAsString(orderDetailsRequest);

        User user = new User(authentication.getName(), (String) authentication.getCredentials(),
                authentication.getAuthorities());

        Mockito.when(userDetailsService.loadUserByUsername("pjs")).thenReturn(user);

        Mockito.when(orderingService.placeOrder(orderDetailsRequest)).thenReturn(Optional.of(orderResponse));

        result = mockMvc
                    .perform(post("/pjs/orders")
                    .contentType(APPLICATION_JSON_UTF8_VALUE).content(output));

        return this;
    }

    PlaceNewOrder then() { return this; }

    PlaceNewOrder statusIs(HttpStatus httpStatus) throws Exception {

        result
            .andDo(print())
            .andExpect(status().is(httpStatus.value()));

        return this;
    }

    PlaceNewOrder orderResponseIs(OrderDetailsResponse orderDetailsResponse) throws Exception {

        result
            .andExpect(jsonPath("$.userId", is(orderDetailsResponse.getUserId())))
            .andExpect(jsonPath("$.orderId", is(orderDetailsResponse.getOrderId())))
            .andExpect(jsonPath("$.orderStatus", is(orderDetailsResponse.getOrderStatus().name())));
        return this;
    }

    public PlaceNewOrder userDetailsService(UserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;

        return this;
    }

    public PlaceNewOrder authentication(UsernamePasswordAuthenticationToken authentication) {

        this.authentication = authentication;

        return this;
    }

    public PlaceNewOrder orderingService(OrderingService orderingService) {

        this.orderingService = orderingService;

        return this;
    }

    public PlaceNewOrder orderDetailsResponse(OrderDetailsResponse orderResponse) {

        this.orderResponse = orderResponse;

        return this;
    }
}
