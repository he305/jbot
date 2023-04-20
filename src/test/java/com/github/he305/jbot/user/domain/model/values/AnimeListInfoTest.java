package com.github.he305.jbot.user.domain.model.values;

import com.github.he305.jbot.common.exceptions.StringValidationException;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimeListInfoTest {

    @Test
    void equals() {
        EqualsVerifier.simple().forClass(AnimeListInfo.class).verify();
    }

    @Test
    void nickname_empty_should_throw() {
        String nickname = "";
        String password = "pass";

        assertThrows(StringValidationException.class, () -> new AnimeListInfo(nickname, password));
    }

    @Test
    void password_empty_should_throw() {
        String nickname = "nick";
        String password = "";

        assertThrows(StringValidationException.class, () -> new AnimeListInfo(nickname, password));
    }

    @Test
    void ok() {
        String nickname = "nick";
        String password = "pass";

        assertDoesNotThrow(() -> new AnimeListInfo(nickname, password));
    }
}