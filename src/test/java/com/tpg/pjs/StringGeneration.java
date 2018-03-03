package com.tpg.pjs;

import org.apache.commons.lang.RandomStringUtils;

public interface StringGeneration {

    default String generateString(int length) {

        return RandomStringUtils.random(length);
    }
}
