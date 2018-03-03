package com.tpg.pjs.validation;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public abstract class ConstraintValidatorTest {

    @Mock
    ValidationConfiguration validationConfiguration;

    @Mock
    ConstraintValidatorContext context;

    @Mock
    javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder builder;

    void configurationSetUp() {

        when(validationConfiguration.getMinLength()).thenReturn(4);

        when(validationConfiguration.getMaxLength()).thenReturn(10);

        when(validationConfiguration.getNoWhitespace()).thenReturn(true);

        when(validationConfiguration.getRequiresUppercase()).thenReturn(true);

        when(validationConfiguration.getRequiresLowercase()).thenReturn(true);

        when(validationConfiguration.getRequiresDigits()).thenReturn(true);

        when(validationConfiguration.getRequiresSymbols()).thenReturn(false);
    }
}
