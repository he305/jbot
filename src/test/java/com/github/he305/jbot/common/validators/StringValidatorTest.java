package com.github.he305.jbot.common.validators;

import com.github.he305.jbot.common.exceptions.StringValidationException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StringValidatorTest {

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @NullSource
    @ValueSource(strings = {"", "\t\n"})
    void validate_null_should_fail(String arg) {
        Assert.assertThrows(StringValidationException.class, () -> StringValidator.validate(arg));
    }

    @Test
    void validate_ok() {
        String s = "1\t\n";

        String actual = StringValidator.validate(s);

        assertEquals(s, actual);
    }
}