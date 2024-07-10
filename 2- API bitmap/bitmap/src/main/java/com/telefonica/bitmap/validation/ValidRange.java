package com.telefonica.bitmap.validation;

import com.telefonica.bitmap.validation.constraintvalidation.RangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation for validating values within a specific range.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Constraint(validatedBy = RangeValidator.class)
public @interface ValidRange {

    /**
     * Mensagem de erro padrão quando a validação falha.
     * @return Mensagem de erro padrão.
     */
    String message() default "List contains values out of range [0, 15]";

    /**
     * Define os grupos de validação aos quais esta restrição pertence.
     * @return Grupos de validação.
     */
    Class<?>[] groups() default {};

    /**
     * Fornece metadados adicionais sobre a validação.
     * @return Carga útil (payload) para a validação.
     */
    Class<? extends Payload>[] payload() default {};
}