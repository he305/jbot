package com.github.he305.jbot.user.domain.model;

import com.github.he305.jbot.user.domain.model.values.AnimeListInfo;
import com.github.he305.jbot.user.domain.model.values.ChatInfo;
import com.github.he305.jbot.user.domain.model.values.UserInfo;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void equals_simple() {
        UUID id = UUID.randomUUID();

        User first = User.getExistent(id, new UserInfo("n", new AnimeListInfo("1", "2")), new ChatInfo("3"));
        User second = User.getExistent(id, new UserInfo("3", new AnimeListInfo("2", "5")), new ChatInfo("7"));

        assertEquals(first, second);
    }

    @Test
    void not_equals() {
        User first = User.create(new UserInfo("n", new AnimeListInfo("1", "2")), new ChatInfo("3"));
        User second = User.create(new UserInfo("3", new AnimeListInfo("2", "5")), new ChatInfo("7"));

        assertNotEquals(first, second);
    }

    @Test
    void changeUserInfo() {
        UserInfo userInfo = new UserInfo("name", new AnimeListInfo("nick", "pass"));

        User user = User.create(new UserInfo("n", new AnimeListInfo("1", "2")), new ChatInfo("3"));
        assertNotEquals(userInfo, user.getUserInfo());

        user.changeUserInfo(userInfo);
        assertEquals(userInfo, user.getUserInfo());
    }
}