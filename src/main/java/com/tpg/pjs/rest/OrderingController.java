package com.tpg.pjs.rest;

import com.tpg.pjs.services.OrderDetailsRequest;
import com.tpg.pjs.services.OrderDetailsResponse;
import com.tpg.pjs.services.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
@RequestMapping("/pjs")
public class OrderingController {

    private final OrderingService orderingService;

    @Autowired
    public OrderingController(OrderingService orderingService) {

        this.orderingService = orderingService;
    }

    @PostMapping("orders")
    @ResponseBody
    @RolesAllowed({"ROLE_PJS_GUEST", "ROLE_PJS_USER"})
    public ResponseEntity<OrderDetailsResponse> placeOrder(@RequestBody OrderDetailsRequest orderDetailsRequest) {

        Optional<OrderDetailsResponse> outcome = orderingService.placeOrder(orderDetailsRequest);

        return ResponseEntity.ok(outcome.get());
    }
}
