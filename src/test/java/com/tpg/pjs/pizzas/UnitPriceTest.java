package com.tpg.pjs.pizzas;

import com.tpg.pjs.ordering.Cost;
import com.tpg.pjs.ordering.Quantity;
import com.tpg.pjs.ordering.UnitPrice;
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
