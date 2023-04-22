package com.github.he305.jbot.user.application.service;

import com.github.he305.jbot.user.domain.abstractions.UserRepository;
import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.domain.model.values.ChatInfo;
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

    @Test
    void getByChatId() {
        String chatId = "123";
        User expected = Mockito.mock(User.class);

        Mockito.when(userRepository.getByChatId(new ChatInfo(chatId))).thenReturn(Optional.of(expected));

        Optional<User> actualOpt = underTest.getByChatId(chatId);
        assertTrue(actualOpt.isPresent());
        assertEquals(expected, actualOpt.get());
    }
}