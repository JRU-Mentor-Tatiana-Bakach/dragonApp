package com.javarush.dragonapp.exception;

import org.springframework.validation.BindingResult;

public class DtoValidationException extends RuntimeException {

    final BindingResult bindingResult;

    public DtoValidationException(final BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}