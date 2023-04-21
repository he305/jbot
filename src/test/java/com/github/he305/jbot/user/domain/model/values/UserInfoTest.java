package com.github.he305.jbot.user.domain.model.values;

import com.github.he305.jbot.common.exceptions.StringValidationException;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserInfoTest {

    @Test
    void name_empty_should_throw() {
        String name = "";

        assertThrows(StringValidationException.class, () -> new UserInfo(name));
    }

    @Test
    void ok_construct() {
        String name = "name";

        assertDoesNotThrow(() -> new UserInfo(name));
    }

    @Test
    void equals() {
        EqualsVerifier.simple().forClass(UserInfo.class).verify();
    }
}