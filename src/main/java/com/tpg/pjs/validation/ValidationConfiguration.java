package com.tpg.pjs.validation;

public interface ValidationConfiguration {

    int getMinLength();

    int getMaxLength();

    boolean getNoWhitespace();

    boolean getRequiresUppercase();

    boolean getRequiresLowercase();

    boolean getRequiresDigits();

    boolean getRequiresSymbols();
}
