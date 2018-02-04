package com.tpg.pjs.services;

import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.ordering.OrderDetailsRequest;
import com.tpg.pjs.persistence.entities.OrderEntity;
import org.springframework.core.convert.converter.Converter;

public interface RequestToDomainConverter extends Converter<OrderDetailsRequest, Order> {

    @Override
    Order convert(OrderDetailsRequest request);
}
