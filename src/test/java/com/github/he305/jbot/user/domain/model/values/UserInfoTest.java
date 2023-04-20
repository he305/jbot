package com.github.he305.jbot.user.domain.model.values;

import com.github.he305.jbot.common.exceptions.StringValidationException;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoTest {

    @Test
    void name_empty_should_throw() {
        String name = "";
        AnimeListInfo animeListInfo = new AnimeListInfo("1", "2");

        assertThrows(StringValidationException.class, () -> new UserInfo(name, animeListInfo));
    }

    @Test
    void ok_construct() {
        String name = "name";
        AnimeListInfo animeListInfo = new AnimeListInfo("1", "2");

        assertDoesNotThrow(() -> new UserInfo(name, animeListInfo));
    }

    @Test
    void equals() {
        EqualsVerifier.simple().forClass(UserInfo.class).verify();
    }
}