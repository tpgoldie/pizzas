package com.tpg.pjs.ordering;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface Pricing {

    static final int PRICING_SCALE = 6;

    static final RoundingMode PRICING_ROUNDING_MODE = RoundingMode.HALF_UP;

    BigDecimal getPrice();
}
