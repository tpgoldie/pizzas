package com.tpg.pjs.validation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsernameConstraintValidatorTest extends ConstraintValidatorTest {

    private UsernameConstraintValidator validator;

    @Before
    public void setUp() {

        validator = new UsernameConstraintValidator(validationConfiguration);
    }

    @Test
    public void invalidateMinLength() {

        String violation = "Username must be 4 or more characters in length.";

        configurationSetUp();

        when(context.buildConstraintViolationWithTemplate(any())).thenReturn(builder);

        boolean actual = validator.isValid("1Aa", context);

        assertFalse("Expected to have failed for username length < min length", actual);

        verify(context).disableDefaultConstraintViolation();

        verify(context).buildConstraintViolationWithTemplate(violation);

        verify(builder).addConstraintViolation();
    }

    @Test
    public void invalidateMaxLength() {

        String violation = "Username must be no more than 10 characters in length.";

        configurationSetUp();

        when(context.buildConstraintViolationWithTemplate(violation)).thenReturn(builder);

        boolean actual = validator.isValid("asasAsa1sasa", context);

        assertFalse("Expected to have failed for username length > max length", actual);

        verify(context).disableDefaultConstraintViolation();

        verify(context).buildConstraintViolationWithTemplate(violation);

        verify(builder).addConstraintViolation();
    }
    @Test
    public void invalidateWhenContainingWhiteSpace() {

        String violation = "Username contains a whitespace character.";

        configurationSetUp();

        when(context.buildConstraintViolationWithTemplate(violation)).thenReturn(builder);

        boolean actual = validator.isValid("Aas1  sa", context);

        assertFalse("Expected to have failed for username containing white space", actual);

        verify(context).disableDefaultConstraintViolation();

        verify(context).buildConstraintViolationWithTemplate(violation);

        verify(builder).addConstraintViolation();
    }

    @Test
    public void invalidateWhenContainingSymbols() {

        String violation = "Username contains the illegal character '!'.";

        configurationSetUp();

        when(context.buildConstraintViolationWithTemplate(violation)).thenReturn(builder);

        boolean actual = validator.isValid("Aas1!sa", context);

        assertFalse("Expected to have failed for username containing at least 1 symbol", actual);

        verify(context).disableDefaultConstraintViolation();

        verify(context).buildConstraintViolationWithTemplate(violation);

        verify(builder).addConstraintViolation();
    }

    @Test
    public void invalidateWhenContainingNoDigits() {

        String violation = "Username must contain 1 or more digit characters.";

        configurationSetUp();

        when(context.buildConstraintViolationWithTemplate(violation)).thenReturn(builder);

        boolean actual = validator.isValid("Aassa", context);

        assertFalse("Expected to have failed for username containing no digits", actual);

        verify(context).disableDefaultConstraintViolation();

        verify(context).buildConstraintViolationWithTemplate(violation);

        verify(builder).addConstraintViolation();
    }

    @Test
    public void invalidateWhenContainingNoUppercaseCharacters() {

        String violation = "Username must contain 1 or more uppercase characters.";

        configurationSetUp();

        when(context.buildConstraintViolationWithTemplate(any())).thenReturn(builder);

        boolean actual = validator.isValid("a1ssa", context);

        assertFalse("Expected to have failed for username containing no uppercase characters", actual);

        verify(context).disableDefaultConstraintViolation();

        verify(context).buildConstraintViolationWithTemplate(violation);

        verify(builder).addConstraintViolation();
    }

    @Test
    public void invalidateWhenContainingNoLowercaseCharacters() {

        String violation = "Username must contain 1 or more lowercase characters.";

        configurationSetUp();

        when(context.buildConstraintViolationWithTemplate(any())).thenReturn(builder);

        boolean actual = validator.isValid("A11S", context);

        assertFalse("Expected to have failed for username containing no lowercase characters", actual);

        verify(context).disableDefaultConstraintViolation();

        verify(context).buildConstraintViolationWithTemplate(violation);

        verify(builder).addConstraintViolation();
    }

    @Test
    public void validateValidPassword() {

        configurationSetUp();

        boolean actual = validator.isValid("asA2sasasa", context);

        assertTrue("Failed to validate valid username on length", actual);

        verify(context, never()).disableDefaultConstraintViolation();

        verify(context, never()).buildConstraintViolationWithTemplate(any());

    }
}
