package com.github.he305.jbot.telegram.application.services;

import com.github.he305.jbot.anime.application.dtos.TokenDto;
import com.github.he305.jbot.anime.application.services.MalAuthenticationService;
import com.github.he305.jbot.user.domain.abstractions.UserService;
import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.domain.model.entities.AnimeListInfo;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import com.github.he305.jbot.user.domain.model.values.ChatInfo;
import com.github.he305.jbot.user.domain.model.values.TokenInfo;
import com.github.he305.jbot.user.domain.model.values.UserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AnimeListAuthorizationServiceImplTest {

    @Mock
    private TelegramBotMessageSender sender;
    @Mock
    private MalAuthenticationService authenticationService;
    @Mock
    private UserService service;

    @InjectMocks
    private AnimeListAuthorizationServiceImpl underTest;

    @Test
    void authorize_noUser() {
        String authCode = "";
        String chatId = "123";
        Mockito.when(service.getByChatId(chatId)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> underTest.authorize(authCode, chatId));
    }

    @Test
    void authorize_success() {
        String authCode = "code";
        String chatId = "123";

        TokenDto dto = new TokenDto("bearer", 123, "234", "123");
        Mockito.when(authenticationService.getToken(authCode)).thenReturn(dto);

        User user = User.create(new UserInfo("test"), new ChatInfo(chatId));
        Mockito.when(service.getByChatId(chatId)).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> underTest.authorize(authCode, chatId));
        assertEquals(1, user.getAnimeListInfoList().size());
    }

    @Test
    void authorize_alreadyExists() {
        String authCode = "code";
        String chatId = "123";

        TokenDto dto = new TokenDto("bearer", 123, "234", "123");
        Mockito.when(authenticationService.getToken(authCode)).thenReturn(dto);

        AnimeListInfo expected = new AnimeListInfo(new TokenInfo(), AnimeListType.MYANIMELIST);
        User user = User.create(new UserInfo("test"), new ChatInfo(chatId));
        user.addAnimeListInfo(expected);
        Mockito.when(service.getByChatId(chatId)).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> underTest.authorize(authCode, chatId));
        assertEquals(1, user.getAnimeListInfoList().size());
    }
}