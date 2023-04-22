package com.github.he305.jbot.telegram.application.actions;

import com.github.he305.jbot.user.domain.abstractions.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class StartActionTest {

    @Mock
    private MessageContext context;
    @Mock
    private UserService userService;

    @InjectMocks
    private StartAction underTest;

    @Test
    void getCommand() {
        String expected = "start";
        assertEquals(expected, underTest.getCommand());
    }

    @Test
    void getDescription() {
        String expected = "registration command";
        assertEquals(expected, underTest.getDescription());
    }

    @Test
    void getMessage_newUser() {
        String name = "Sample";
        String expected = String.format("Hello, %s! Feel free to use /help command", name);

        User user = Mockito.mock(User.class);
        Mockito.when(userService.getByChatId(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(context.chatId()).thenReturn(0L);
        Mockito.when(context.user()).thenReturn(user);
        Mockito.when(user.getFirstName()).thenReturn(name);

        String actual = underTest.getMessage(context);
        assertEquals(expected, actual);
    }

    @Test
    void getMessage_existingUser() {
        String name = "Sample";
        String expected = String.format("Welcome back, %s! Feel free to use /help command", name);

        User user = Mockito.mock(User.class);
        com.github.he305.jbot.user.domain.model.User repoUser = Mockito.mock(com.github.he305.jbot.user.domain.model.User.class);
        Mockito.when(userService.getByChatId(Mockito.anyString())).thenReturn(Optional.of(repoUser));
        Mockito.when(context.chatId()).thenReturn(0L);
        Mockito.when(context.user()).thenReturn(user);
        Mockito.when(user.getFirstName()).thenReturn(name);

        String actual = underTest.getMessage(context);
        assertEquals(expected, actual);
    }
}