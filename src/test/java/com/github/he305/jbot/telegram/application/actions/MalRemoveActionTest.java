package com.github.he305.jbot.telegram.application.actions;

import com.github.he305.jbot.telegram.application.services.GetUserByContextQuery;
import com.github.he305.jbot.user.domain.abstractions.UserService;
import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.abilitybots.api.objects.MessageContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MalRemoveActionTest {

    @Mock
    private MessageContext context;
    @Mock
    private GetUserByContextQuery getUserByContextQuery;
    @Mock
    private UserService userService;

    @InjectMocks
    private MalRemoveAction underTest;

    @Test
    void getCommand() {
        String expected = "mal_remove";
        assertEquals(expected, underTest.getCommand());
    }

    @Test
    void getDescription() {
        String expected = "remove existing myanimelist account";
        assertEquals(expected, underTest.getDescription());
    }

    @Test
    void getMessage_notFound() {
        String expected = "Myanimelist account hasn't been found";

        User user = Mockito.mock(User.class);
        Mockito.when(user.removeAnimeListInfoType(AnimeListType.MYANIMELIST)).thenReturn(false);
        Mockito.when(getUserByContextQuery.execute(context)).thenReturn(user);

        String actual = underTest.getMessage(context);
        assertEquals(expected, actual);
    }

    @Test
    void getMessage_success() {
        String expected = "Successfully deleted myanimelist account";

        User user = Mockito.mock(User.class);
        Mockito.when(user.removeAnimeListInfoType(AnimeListType.MYANIMELIST)).thenReturn(true);
        Mockito.when(getUserByContextQuery.execute(context)).thenReturn(user);

        String actual = underTest.getMessage(context);
        assertEquals(expected, actual);
    }

}