package com.senla.web.validation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

/** @author Aliaksei Kaspiarovich */
@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldsValueMatch {

    String message() default "Fields values don't match!";

    String field();

    String fieldMatch();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
