package com.telefonica.bitmap.rest;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * Represents API errors containing a list of error messages.
 */
public class ApiErrors {

    /**
     * List of error messages.
     */
    @Getter
    private List<String> errors;

    /**
     * Constructor that initializes ApiErrors with a list of error messages.
     * @param errors List of error messages.
     */
    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    /**
     * Constructor that initializes ApiErrors with a single error message.
     * @param errorMessage Single error message.
     */
    public ApiErrors(String mensagemErro){
        this.errors = Arrays.asList(mensagemErro);
    }
}
