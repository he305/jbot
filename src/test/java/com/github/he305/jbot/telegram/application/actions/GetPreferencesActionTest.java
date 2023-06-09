package com.github.he305.jbot.telegram.application.actions;

import com.github.he305.jbot.common.enums.AnimeAudioSource;
import com.github.he305.jbot.common.enums.AnimeSubsSource;
import com.github.he305.jbot.telegram.application.services.GetUserByContextQuery;
import com.github.he305.jbot.user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.abilitybots.api.objects.MessageContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GetPreferencesActionTest {

    @Mock
    private GetUserByContextQuery getUserByContextQuery;

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
        MessageContext ctx = Mockito.mock(MessageContext.class);

        AnimeAudioSource audioSource = AnimeAudioSource.JAPANESE;
        AnimeSubsSource subsSource = AnimeSubsSource.SUBSPLEASE;
        User user = Mockito.mock(User.class);
        Mockito.when(user.getAudioSource()).thenReturn(audioSource);
        Mockito.when(user.getSubsSource()).thenReturn(subsSource);
        Mockito.when(getUserByContextQuery.execute(ctx)).thenReturn(user);

        String expected = "Audio source: japanese, subs source: subsplease";
        String actual = underTest.getMessage(ctx);
        assertEquals(expected, actual);
    }
}