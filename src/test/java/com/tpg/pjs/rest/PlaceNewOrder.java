package com.tpg.pjs.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpg.pjs.services.OrderDetailsRequest;
import com.tpg.pjs.services.OrderDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

public class PlaceNewOrder {

    static PlaceNewOrder given(MockMvc mockMvc) {

        return new PlaceNewOrder(mockMvc);
    }

    private final MockMvc mockMvc;

    private OrderDetailsRequest orderDetailsRequest;

    private ResultActions result;

    private ObjectMapper objectMapper;

    private PlaceNewOrder(MockMvc mockMvc) {

        this.mockMvc = mockMvc;
    }

    PlaceNewOrder anOrderDetailsRequest(OrderDetailsRequest orderRequest) {

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
            .andExpect(jsonPath("$.userId", is(orderDetailsResponse.getOrderId())))
            .andExpect(jsonPath("$.userId", is(orderDetailsResponse.getOrderStatus())));
        return this;
    }
}
