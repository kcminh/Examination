package com.t3h.elibrary.common;

import org.passay.*;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {
    public static boolean isValid(String password) {
        List<Rule> rules = new ArrayList<>();
        rules.add(new LengthRule(8, 20));// password length should be in between
        rules.add(new WhitespaceRule());// no whitespace allowed
        rules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1));//At least one Upper-case character
        rules.add(new CharacterRule(EnglishCharacterData.LowerCase, 1));//At least one Lower-case character
        rules.add(new CharacterRule(EnglishCharacterData.Digit, 1));  //At least one digit
        rules.add(new CharacterRule(EnglishCharacterData.Special, 1));//At least one special character

        org.passay.PasswordValidator validator = new org.passay.PasswordValidator(rules);
        PasswordData passwordData = new PasswordData(password);
        RuleResult result = validator.validate(passwordData);

        return result.isValid();
    }
}
