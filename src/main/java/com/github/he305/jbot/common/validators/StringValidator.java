package com.github.he305.jbot.common.validators;

import com.github.he305.jbot.common.exceptions.StringValidationException;

public final class StringValidator {
    private StringValidator(){

    }
    public static String validate(String str) {
        if (str == null || str.isBlank()) {
            throw new StringValidationException();
        }
        return str;
    }
}
