package com.github.he305.jbot.user.infra.mapper;

import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.domain.model.entities.AnimeListInfo;
import com.github.he305.jbot.user.domain.model.enums.AnimeAudioSource;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import com.github.he305.jbot.user.domain.model.enums.AnimeSubsSource;
import com.github.he305.jbot.user.domain.model.values.ChatInfo;
import com.github.he305.jbot.user.domain.model.values.UserInfo;
import com.github.he305.jbot.user.infra.data.postgresql.AnimeListInfoJpa;
import com.github.he305.jbot.user.infra.data.postgresql.ChatInfoJpa;
import com.github.he305.jbot.user.infra.data.postgresql.UserInfoJpa;
import com.github.he305.jbot.user.infra.data.postgresql.UserJpa;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserPostgresMapperTest {
    private final UserPostgresMapperImpl mapper = new UserPostgresMapperImpl();

    @Test
    void domainToData() {
        UUID id = UUID.randomUUID();
        UserJpa expected = new UserJpa(id, new UserInfoJpa("name"), new ChatInfoJpa("chatId"));
        expected.setAudioSource(AnimeAudioSource.JAPANESE);
        expected.setSubsSource(AnimeSubsSource.SUBSPLEASE);
        AnimeListInfoJpa listInfoJpa = new AnimeListInfoJpa(id, "nick", "pass", AnimeListType.MYANIMELIST);
        expected.addAnimeListInfoJpa(listInfoJpa);

        User user = new User(id, new UserInfo("name"), new ChatInfo("chatId"));
        user.setSubsSource(AnimeSubsSource.SUBSPLEASE);
        AnimeListInfo animeListInfo = new AnimeListInfo(id, "nick", "pass", AnimeListType.MYANIMELIST);
        user.addAnimeListInfo(animeListInfo);

        UserJpa actual = mapper.domainToData(user);
        assertEquals(expected, actual);
        assertEquals(expected.getUserInfo(), actual.getUserInfo());
        assertEquals(expected.getAnimeListInfoList(), actual.getAnimeListInfoList());
        assertEquals(expected.getChatInfo(), actual.getChatInfo());
        assertEquals(expected.getAudioSource(), actual.getAudioSource());
        assertEquals(expected.getSubsSource(), actual.getSubsSource());
    }

    @Test
    void dataToDomain() {
        UUID id = UUID.randomUUID();
        User expected = new User(id, new UserInfo("name"), new ChatInfo("chatId"));
        expected.setSubsSource(AnimeSubsSource.SUBSPLEASE);
        AnimeListInfo animeListInfo = new AnimeListInfo(id, "nick", "pass", AnimeListType.MYANIMELIST);
        expected.addAnimeListInfo(animeListInfo);

        UserJpa data = new UserJpa(id, new UserInfoJpa("name"), new ChatInfoJpa("chatId"));
        data.setAudioSource(AnimeAudioSource.JAPANESE);
        data.setSubsSource(AnimeSubsSource.SUBSPLEASE);
        AnimeListInfoJpa listInfoJpa = new AnimeListInfoJpa(id, "nick", "pass", AnimeListType.MYANIMELIST);
        data.addAnimeListInfoJpa(listInfoJpa);

        User actual = mapper.dataToDomain(data);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected, actual);
        assertEquals(expected.getUserInfo(), actual.getUserInfo());
        assertEquals(expected.getAnimeListInfoList(), actual.getAnimeListInfoList());
        assertEquals(expected.getChatInfo(), actual.getChatInfo());
        assertEquals(expected.getAudioSource(), actual.getAudioSource());
        assertEquals(expected.getSubsSource(), actual.getSubsSource());
    }
}