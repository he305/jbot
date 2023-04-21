package com.github.he305.jbot.user.domain.model;

import com.github.he305.jbot.user.domain.exceptions.AnimeListInfoAlreadyExist;
import com.github.he305.jbot.user.domain.model.entities.AnimeListInfo;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import com.github.he305.jbot.user.domain.model.values.ChatInfo;
import com.github.he305.jbot.user.domain.model.values.UserInfo;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void equals_simple() {
        UUID id = UUID.randomUUID();

        User first = User.getExistent(id, new UserInfo("n"), new ChatInfo("3"));
        User second = User.getExistent(id, new UserInfo("3"), new ChatInfo("7"));

        assertEquals(first, second);
    }

    @Test
    void not_equals() {
        User first = User.create(new UserInfo("n"), new ChatInfo("3"));
        User second = User.create(new UserInfo("3"), new ChatInfo("7"));

        assertNotEquals(first, second);
    }

    @Test
    void addAnimeListInfo() {
        UUID id = UUID.randomUUID();
        AnimeListInfo first = new AnimeListInfo(id, "nick", "pass", AnimeListType.MYANIMELIST);
        AnimeListInfo second = new AnimeListInfo(id, "1", "2", AnimeListType.MYANIMELIST);

        User user = User.create(new UserInfo("n"), new ChatInfo("3"));

        assertEquals(0, user.getAnimeListInfoList().size());
        user.addAnimeListInfo(first);

        assertEquals(1, user.getAnimeListInfoList().size());
        assertEquals(second, user.getAnimeListInfoList().get(0));

        assertThrows(AnimeListInfoAlreadyExist.class, () -> user.addAnimeListInfo(second));
    }
}