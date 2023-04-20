package com.github.he305.jbot.user.application.service;

import com.github.he305.jbot.user.domain.abstractions.UserRepository;
import com.github.he305.jbot.user.domain.model.User;
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
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl underTest;

    @Test
    void save() {
        User expected = new User(UUID.randomUUID(), null, null);
        assertDoesNotThrow(() -> underTest.save(expected));
    }

    @Test
    void getAll() {
        User expected = new User(UUID.randomUUID(), null, null);
        Mockito.when(userRepository.getAll()).thenReturn(List.of(expected));

        List<User> actualList = underTest.getAll();
        assertEquals(1, actualList.size());
        assertEquals(expected, actualList.get(0));
    }
}