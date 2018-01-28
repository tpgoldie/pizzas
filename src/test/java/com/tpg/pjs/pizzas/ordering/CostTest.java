package com.tpg.pjs.pizzas.ordering;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class CostTest {

    private Cost cost;

    @Before
    public void setUp() {

        cost = new Cost(new BigDecimal(2.35));
    }

    @Test
    public void adding() {

        Cost c1 = new Cost(new BigDecimal(1.23));

        Cost actual = cost.add(c1);

        assertThat(actual).isEqualTo(new Cost(new BigDecimal(1.23 + 2.35)));
        assertThat(cost).isEqualTo(new Cost(new BigDecimal(2.35)));
        assertThat(c1).isEqualTo(new Cost(new BigDecimal(1.23)));
    }
}
