package com.telefonica.bitmap.validation.constraintvalidation;

import com.telefonica.bitmap.validation.ValidRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class RangeValidator implements ConstraintValidator<ValidRange, List<Integer>> {

    private static final int ZERO = 0;

    private static final int FIFTEEN = 15;

    @Override
    public void initialize(ValidRange constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<Integer> values, ConstraintValidatorContext context) {
        if (values == null) {
            return false;
        }
        for (Integer value : values) {
            if (value < ZERO || value > FIFTEEN) {
                return false;
            }
        }
        return true;
    }
}
