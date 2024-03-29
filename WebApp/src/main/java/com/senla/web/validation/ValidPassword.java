package com.senla.web.validation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

/** @author Aliaksei Kaspiarovich */
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidPassword {

    String message() default "Invalid Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
