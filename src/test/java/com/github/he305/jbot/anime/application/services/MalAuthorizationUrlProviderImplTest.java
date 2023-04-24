package com.github.he305.jbot.anime.application.services;

import com.github.he305.jbot.anime.configuration.MalApiConfig;
import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.domain.model.values.ChatInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MalAuthorizationUrlProviderImplTest {

    @Mock
    private CodeChallengeProvider codeChallengeProvider;
    @Mock
    private MalApiConfig malApiConfig;

    @InjectMocks
    private MalAuthorizationUrlProviderImpl underTest;

    @Test
    void getAuthUrl() {
        String expected = "https://myanimelist.net/v1/oauth2/authorize?response_type=code&client_id=123&code_challenge=chall&state=state";

        User user = Mockito.mock(User.class);
        ChatInfo chatInfo = new ChatInfo("state");

        Mockito.when(user.getChatInfo()).thenReturn(chatInfo);
        Mockito.when(codeChallengeProvider.getCodeChallenge()).thenReturn("chall");
        Mockito.when(malApiConfig.getClientId()).thenReturn("123");

        String actual = underTest.getAuthUrl(user);
        assertEquals(expected, actual);
    }
}