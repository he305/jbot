package com.github.he305.jbot.telegram.application.actions;

import com.github.he305.jbot.anime.application.services.MalAuthorizationUrlProvider;
import com.github.he305.jbot.telegram.application.services.GetUserByContextQuery;
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
class MalAuthActionTest {

    @Mock
    private MessageContext context;
    @Mock
    private GetUserByContextQuery getUserByContextQuery;
    @Mock
    private MalAuthorizationUrlProvider malAuthorizationUrlProvider;

    @InjectMocks
    private MalAuthAction underTest;

    @Test
    void getCommand() {
        String expected = "mal_auth";
        assertEquals(expected, underTest.getCommand());
    }

    @Test
    void getDescription() {
        String expected = "provides link to authorize myanimelist account";
        assertEquals(expected, underTest.getDescription());
    }

    @Test
    void getMessage_accountExists() {
        String expected = "Myanimelist account associated with you already exists. Remove the previous one to add new";


        User user = Mockito.mock(User.class);
        Mockito.when(user.animeListInfoWithTypeExists(AnimeListType.MYANIMELIST)).thenReturn(true);
        Mockito.when(getUserByContextQuery.execute(context)).thenReturn(user);

        String actual = underTest.getMessage(context);
        assertEquals(expected, actual);
    }

    @Test
    void getMessage_success() {
        String url = "url";
        String expected = "Use this url to authorize myanimelist account: url";


        User user = Mockito.mock(User.class);
        Mockito.when(user.animeListInfoWithTypeExists(AnimeListType.MYANIMELIST)).thenReturn(false);
        Mockito.when(getUserByContextQuery.execute(context)).thenReturn(user);
        Mockito.when(malAuthorizationUrlProvider.getAuthUrl(user)).thenReturn(url);

        String actual = underTest.getMessage(context);
        assertEquals(expected, actual);
    }
}