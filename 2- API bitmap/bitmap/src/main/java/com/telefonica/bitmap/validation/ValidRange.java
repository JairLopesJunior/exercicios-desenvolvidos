package com.telefonica.bitmap.validation;

import com.telefonica.bitmap.validation.constraintvalidation.RangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Constraint(validatedBy = RangeValidator.class)
public @interface ValidRange {

    String message() default "List contains values out of range [0, 15]";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}