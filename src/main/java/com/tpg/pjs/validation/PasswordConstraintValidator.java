package com.tpg.pjs.validation;

import com.google.common.base.Joiner;
import org.passay.RuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidatorContext;

@Component
public class PasswordConstraintValidator extends UserDetailConstraintValidator<ValidPassword, String> {

    @Autowired
    public PasswordConstraintValidator(ValidationConfiguration validationConfiguration) {

        super(validationConfiguration);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        Outcome outcome = validate(password);

        if (outcome.getRuleResult().isValid()) { return true; }

        context.disableDefaultConstraintViolation();

        String output = Joiner.on(",").join(outcome.getPasswordValidator().getMessages(outcome.getRuleResult()));

        context.buildConstraintViolationWithTemplate(output).addConstraintViolation();

        return false;
    }
}
