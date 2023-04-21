package com.github.he305.jbot.user.infra.repository;

import com.github.he305.jbot.user.domain.model.User;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}