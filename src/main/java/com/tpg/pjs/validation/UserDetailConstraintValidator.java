package com.tpg.pjs.validation;

import lombok.Builder;
import lombok.Getter;
import org.passay.*;

import javax.validation.ConstraintValidator;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public abstract class UserDetailConstraintValidator<T extends Annotation, U> implements ConstraintValidator<T, U> {

    final ValidationConfiguration validationConfiguration;

    protected UserDetailConstraintValidator(ValidationConfiguration validationConfiguration) {

        this.validationConfiguration = validationConfiguration;
    }

    @Override
    public void initialize(T value) {
    }

    private List<Rule> configureRules() {

        List<Rule> rules = new ArrayList<>();

        configureLengthRule(rules);

        configureWhitespaceRule(rules);

        configureLowercaseRule(rules);

        configureUppercaseRule(rules);

        configureDigitsRule(rules);

        configureIllegalCharactersRule(rules);

        return rules;
    }

    private void configureLengthRule(List<Rule> rules) {
        rules.add(new LengthRule(validationConfiguration.getMinLength(), validationConfiguration.getMaxLength()));
    }

    private void configureIllegalCharactersRule(List<Rule> rules) {
        if (validationConfiguration.getRequiresSymbols()) {
            rules.add(new CharacterRule(EnglishCharacterData.Special, 1));
        }
        else {
            rules.add(new IllegalCharacterRule("!£$%*&^".toCharArray()));
        }
    }

    private void configureDigitsRule(List<Rule> rules) {
        if (validationConfiguration.getRequiresDigits()) {
            rules.add(new CharacterRule(EnglishCharacterData.Digit, 1));
        }
    }

    private void configureUppercaseRule(List<Rule> rules) {
        if (validationConfiguration.getRequiresUppercase()) {
            rules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1));
        }
    }

    private void configureLowercaseRule(List<Rule> rules) {
        if (validationConfiguration.getRequiresLowercase()) {
            rules.add(new CharacterRule(EnglishCharacterData.LowerCase, 1));
        }
    }

    private void configureWhitespaceRule(List<Rule> rules) {
        if (validationConfiguration.getNoWhitespace()) {
            rules.add(new WhitespaceRule());
        }
    }

    Outcome validate(String value) {

        List<Rule> rules = configureRules();

        PasswordValidator passwordValidator = new PasswordValidator(rules);

        RuleResult ruleResult = passwordValidator.validate(new PasswordData(value));

        return Outcome.builder().ruleResult(ruleResult).passwordValidator(passwordValidator).build();
    }

    @Getter
    @Builder
    static class Outcome {

        private final PasswordValidator passwordValidator;

        private final RuleResult ruleResult;
    }
}
