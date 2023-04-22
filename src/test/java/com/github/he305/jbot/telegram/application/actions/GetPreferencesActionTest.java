package com.github.he305.jbot.telegram.application.actions;

import com.github.he305.jbot.common.enums.AnimeAudioSource;
import com.github.he305.jbot.common.enums.AnimeSubsSource;
import com.github.he305.jbot.telegram.application.exceptions.UnexpectedStateException;
import com.github.he305.jbot.user.domain.abstractions.UserService;
import com.github.he305.jbot.user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.abilitybots.api.objects.MessageContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class GetPreferencesActionTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private GetPreferencesAction underTest;

    @Test
    void getCommand() {
        String expected = "get_preferences";
        assertEquals(expected, underTest.getCommand());
    }

    @Test
    void getDescription() {
        String expected = "display current anime source preferences";
        assertEquals(expected, underTest.getDescription());
    }

    @Test
    void getMessage_ok() {
        long chatId = 123;
        MessageContext ctx = Mockito.mock(MessageContext.class);
        Mockito.when(ctx.chatId()).thenReturn(chatId);

        AnimeAudioSource audioSource = AnimeAudioSource.JAPANESE;
        AnimeSubsSource subsSource = AnimeSubsSource.SUBSPLEASE;
        User user = Mockito.mock(User.class);
        Mockito.when(user.getAudioSource()).thenReturn(audioSource);
        Mockito.when(user.getSubsSource()).thenReturn(subsSource);
        Mockito.when(userService.getByChatId(String.valueOf(chatId))).thenReturn(Optional.of(user));

        String expected = "Audio source: japanese, subs source: subsplease";
        String actual = underTest.getMessage(ctx);
        assertEquals(expected, actual);
    }

    @Test
    void getMessage_userNotFound() {
        long chatId = 123;
        org.telegram.telegrambots.meta.api.objects.User user = Mockito.mock(org.telegram.telegrambots.meta.api.objects.User.class);
        MessageContext ctx = Mockito.mock(MessageContext.class);
        Mockito.when(ctx.chatId()).thenReturn(chatId);
        Mockito.when(ctx.user()).thenReturn(user);
        Mockito.when(user.getFirstName()).thenReturn("");
        Mockito.when(userService.getByChatId(String.valueOf(chatId))).thenReturn(Optional.empty());

        assertThrows(UnexpectedStateException.class, () -> underTest.getMessage(ctx));
    }
}