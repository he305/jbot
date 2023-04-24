package com.github.he305.jbot.user.domain.model.entities;

import com.github.he305.jbot.common.exceptions.StringValidationException;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import com.github.he305.jbot.user.domain.model.values.TokenInfo;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AnimeListInfoTest {

    @Test
    void authorizationOnly() {
        TokenInfo token = new TokenInfo("123", "345", 50);
        AnimeListInfo info = new AnimeListInfo(UUID.randomUUID(), token, AnimeListType.MYANIMELIST);
        assertNull(info.getNickname());
        assertNull(info.getPassword());
        assertEquals(token, info.getTokenInfo());
    }

    @Test
    void not_equals() {
        String nickname = "nick";
        String password = "pass";

        AnimeListInfo first = new AnimeListInfo(nickname, password, AnimeListType.MYANIMELIST);
        AnimeListInfo second = new AnimeListInfo(nickname, password, AnimeListType.MYANIMELIST);

        assertNotEquals(first, second);
    }

    @Test
    void equals() {
        UUID id = UUID.randomUUID();

        AnimeListInfo first = new AnimeListInfo(id, "1", "2", AnimeListType.MYANIMELIST);
        AnimeListInfo second = new AnimeListInfo(id, "3", "4", AnimeListType.MYANIMELIST);

        assertEquals(first, second);
    }

    @Test
    void nickname_empty_should_throw() {
        String nickname = "";
        String password = "pass";

        assertThrows(StringValidationException.class, () -> new AnimeListInfo(nickname, password, AnimeListType.MYANIMELIST));
    }

    @Test
    void password_empty_should_throw() {
        String nickname = "nick";
        String password = "";

        assertThrows(StringValidationException.class, () -> new AnimeListInfo(nickname, password, AnimeListType.MYANIMELIST));
    }

    @Test
    void ok() {
        String nickname = "nick";
        String password = "pass";

        assertDoesNotThrow(() -> new AnimeListInfo(nickname, password, AnimeListType.MYANIMELIST));
    }

    @Test
    void changePassword() {
        String nickname = "nick";
        String password = "pass";

        AnimeListInfo animeListInfo = new AnimeListInfo(nickname, password, AnimeListType.MYANIMELIST);

        String newPassword = "newPass";

        animeListInfo.changePassword(newPassword);

        assertEquals(newPassword, animeListInfo.getPassword());
    }
}