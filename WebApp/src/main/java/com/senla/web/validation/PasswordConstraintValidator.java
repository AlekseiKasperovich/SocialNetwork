package com.senla.web.validation;

import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.passay.*;

/** @author Aliaksei Kaspiarovich */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }
        PasswordValidator validator =
                new PasswordValidator(
                        Arrays.asList(
                                new LengthRule(5, 16),
                                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                                new CharacterRule(EnglishCharacterData.Digit, 1),
                                new WhitespaceRule()));
        RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }
}
