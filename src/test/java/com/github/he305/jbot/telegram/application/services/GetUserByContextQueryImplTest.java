package com.github.he305.jbot.telegram.application.services;

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
class GetUserByContextQueryImplTest {

    @Mock
    private MessageContext context;
    @Mock
    private UserService userService;

    @InjectMocks
    private GetUserByContextQueryImpl underTest;

    @Test
    void execute_newUser() {
        String name = "Sample";

        User user = Mockito.mock(User.class);
        Mockito.when(userService.getByChatId(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(context.chatId()).thenReturn(0L);
        Mockito.when(context.user()).thenReturn(user);
        Mockito.when(user.getFirstName()).thenReturn(name);


        com.github.he305.jbot.user.domain.model.User actual = underTest.execute(context);
        assertEquals(name, actual.getUserInfo().getName());
    }

    @Test
    void execute_existingUser() {
        com.github.he305.jbot.user.domain.model.User expected = Mockito.mock(com.github.he305.jbot.user.domain.model.User.class);
        Mockito.when(userService.getByChatId(Mockito.anyString())).thenReturn(Optional.of(expected));
        Mockito.when(context.chatId()).thenReturn(0L);

        com.github.he305.jbot.user.domain.model.User actual = underTest.execute(context);
        assertEquals(expected, actual);
    }
}