package com.github.he305.jbot.user.infra.repository;

import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.infra.data.UserDataMongo;
import com.github.he305.jbot.user.infra.jpa.UserJpa;
import com.github.he305.jbot.user.infra.mapper.UserMongoMapper;
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
class UserRepositoryMongoTest {

    @Mock
    private UserMongoMapper mapper;
    @Mock
    private UserJpa userJpa;

    @InjectMocks
    private UserRepositoryMongo underTest;

    @Test
    void getAll() {
        User expected = new User(UUID.randomUUID(), null, null);
        UserDataMongo data = new UserDataMongo();

        Mockito.when(mapper.dataToDomainCollection(List.of(data))).thenReturn(List.of(expected));
        Mockito.when(userJpa.findAll()).thenReturn(List.of(data));

        List<User> actualList = underTest.getAll();
        assertEquals(1, actualList.size());
        assertEquals(expected, actualList.get(0));
    }

    @Test
    void save() {
        User expected = new User(UUID.randomUUID(), null, null);
        UserDataMongo data = new UserDataMongo();

        Mockito.when(mapper.domainToData(expected)).thenReturn(data);

        assertDoesNotThrow(() -> underTest.save(expected));
    }
}