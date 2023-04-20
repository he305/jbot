package com.github.he305.jbot.user.infra.mapper;

import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.domain.model.values.AnimeListInfo;
import com.github.he305.jbot.user.domain.model.values.ChatInfo;
import com.github.he305.jbot.user.domain.model.values.UserInfo;
import com.github.he305.jbot.user.infra.data.AnimeListInfoDataMongo;
import com.github.he305.jbot.user.infra.data.ChatInfoDataMongo;
import com.github.he305.jbot.user.infra.data.UserDataMongo;
import com.github.he305.jbot.user.infra.data.UserInfoDataMongo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserMongoMapperTest {

    @Autowired
    private UserMongoMapper mapper;

    @Test
    void dataToDomain() {
        UUID id = UUID.randomUUID();

        User expected = new User(
                id,
                new UserInfo("name", new AnimeListInfo("nickname", "password")),
                new ChatInfo("chatId")
        );

        UserDataMongo data = new UserDataMongo(null,
                id,
                new UserInfoDataMongo("name", new AnimeListInfoDataMongo("nickname", "password")),
                new ChatInfoDataMongo("chatId"));


        User actual = mapper.dataToDomain(data);
        assertEquals(expected, actual);
    }

    @Test
    void domainToData() {
        UUID id = UUID.randomUUID();
        UserDataMongo expected = new UserDataMongo(null,
                id,
                new UserInfoDataMongo("name", new AnimeListInfoDataMongo("nickname", "password")),
                new ChatInfoDataMongo("chatId"));

        User user = new User(
                id,
                new UserInfo("name", new AnimeListInfo("nickname", "password")),
                new ChatInfo("chatId")
        );

        UserDataMongo actual = mapper.domainToData(user);
        assertEquals(expected, actual);
    }
}