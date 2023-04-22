package com.github.he305.jbot.user.infra.repository;

import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.domain.model.values.ChatInfo;
import com.github.he305.jbot.user.infra.data.postgresql.ChatInfoJpa;
import com.github.he305.jbot.user.infra.data.postgresql.UserJpa;
import com.github.he305.jbot.user.infra.jpa.UserPostgresJpa;
import com.github.he305.jbot.user.infra.mapper.UserPostgresMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryPostgresTest {

    @Mock
    private UserPostgresMapper mapper;
    @Mock
    private UserPostgresJpa userJpa;

    @InjectMocks
    private UserRepositoryPostgres underTest;

    @Test
    void getAll() {
        User expected = new User(UUID.randomUUID(), null, null);
        UserJpa data = new UserJpa();

        Mockito.when(mapper.dataToDomain(data)).thenReturn(expected);
        Mockito.when(userJpa.findAll()).thenReturn(List.of(data));

        List<User> actualList = underTest.getAll();
        assertEquals(1, actualList.size());
        assertEquals(expected, actualList.get(0));
    }

    @Test
    void save() {
        User expected = new User(UUID.randomUUID(), null, null);
        UserJpa data = new UserJpa();

        Mockito.when(mapper.domainToData(expected)).thenReturn(data);

        assertDoesNotThrow(() -> underTest.save(expected));
    }

    @Test
    void getByChatId() {
        ChatInfo chatInfo = new ChatInfo("123");
        ChatInfoJpa jpa = new ChatInfoJpa(chatInfo.getChatId());

        UserJpa returned = Mockito.mock(UserJpa.class);
        User expected = Mockito.mock(User.class);
        Mockito.when(userJpa.findByChatInfo(jpa)).thenReturn(Optional.of(returned));
        Mockito.when(mapper.dataToDomain(returned)).thenReturn(expected);

        Optional<User> actualOpt = underTest.getByChatId(chatInfo);
        assertTrue(actualOpt.isPresent());
        assertEquals(expected, actualOpt.get());
    }
}