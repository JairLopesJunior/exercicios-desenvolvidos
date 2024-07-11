package com.telefonica.bitmap.validation.constraintvalidation;

import com.telefonica.bitmap.validation.ValidRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

/**
 * Validator class for validating a list of integers within a specific range.
 */
public class RangeValidator implements ConstraintValidator<ValidRange, List<Integer>> {

    /**
     * Método de inicialização da validação.
     * @param constraintAnnotation A anotação de restrição a ser inicializada.
     */
    @Override
    public void initialize(ValidRange constraintAnnotation) {
    }

    /**
     * Método de validação que verifica se todos os valores na lista estão dentro da faixa [0, 15].
     * @param values Lista de valores a serem validados.
     * @param context Contexto de validação que pode ser utilizado para informações adicionais e customizações.
     * @return true se todos os valores estiverem dentro da faixa especificada, false caso contrário.
     */
    @Override
    public boolean isValid(List<Integer> values, ConstraintValidatorContext context) {
        if (values == null) {
            return false;
        }

        for (Integer value : values) {
            if (value == null || value < 0 || value > 15) {
                return false;
            }
        }

        return true;
    }
}
