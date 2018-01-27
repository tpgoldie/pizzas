package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.ordering.Cost;
import com.tpg.pizzas.domain.ordering.Quantity;
import com.tpg.pizzas.domain.ordering.UnitPrice;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class UnitPriceTest {

    private UnitPrice unitPrice;

    @Before
    public void setUp() {

        unitPrice = new UnitPrice(new BigDecimal(4.50));
    }

    @Test
    public void multiplyByQuantity() {

        Cost actual = unitPrice.multiplyBy(new Quantity(3));

        assertThat(actual).isEqualTo(new Cost(new BigDecimal(4.5 * 3)));
    }
}
