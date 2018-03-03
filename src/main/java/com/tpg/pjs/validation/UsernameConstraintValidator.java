package com.tpg.pjs.validation;

import com.google.common.base.Joiner;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidatorContext;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class UsernameConstraintValidator extends UserDetailConstraintValidator<ValidUsername, String> {

    @Autowired
    public UsernameConstraintValidator(ValidationConfiguration validationConfiguration) {

        super(validationConfiguration);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        Outcome outcome = validate(username);

        if (outcome.getRuleResult().isValid()) { return true; }

        context.disableDefaultConstraintViolation();

        List<String> msgs = outcome.getPasswordValidator().getMessages(outcome.getRuleResult())
                .stream().map(this::translate).collect(toList());

        String output = Joiner.on(",").join(msgs);

        context.buildConstraintViolationWithTemplate(output).addConstraintViolation();

        return false;
    }

    private String translate(String value) {

        if (value.contains("password")) { return StringUtils.replace(value, "password", "username"); }

        if (value.contains("Password")) { return StringUtils.replace(value, "Password", "Username"); }

        return value;
    }
}
