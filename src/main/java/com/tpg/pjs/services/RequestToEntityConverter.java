package com.tpg.pjs.services;

import com.tpg.pjs.persistence.entities.OrderEntity;
import org.springframework.core.convert.converter.Converter;

public interface RequestToEntityConverter extends Converter<OrderDetailsRequest, OrderEntity>, DateHandling {

    @Override
    OrderEntity convert(OrderDetailsRequest request);
}
