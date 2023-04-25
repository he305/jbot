package com.github.he305.jbot.telegram.application.services;

import com.github.he305.jbot.anime.application.dtos.TokenDto;
import com.github.he305.jbot.anime.application.services.MalAuthenticationService;
import com.github.he305.jbot.user.domain.abstractions.UserService;
import com.github.he305.jbot.user.domain.exceptions.AnimeListInfoAlreadyExist;
import com.github.he305.jbot.user.domain.model.entities.AnimeListInfo;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import com.github.he305.jbot.user.domain.model.values.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimeListAuthorizationServiceImpl implements AnimeListAuthorizationService {
    private final TelegramBotMessageSender sender;
    private final MalAuthenticationService authenticationService;
    private final UserService userService;

    @Override
    public void authorize(String authorizationCode, String chatId) {
        userService.getByChatId(chatId).ifPresentOrElse(
                user -> {
                    TokenDto authData = authenticationService.getToken(authorizationCode);
                    TokenInfo tokenInfo = new TokenInfo(authData.getAccessToken(), authData.getRefreshToken(), authData.getExpiresIn());

                    AnimeListInfo info = new AnimeListInfo(tokenInfo, AnimeListType.MYANIMELIST);
                    try {
                        user.addAnimeListInfo(info);
                        userService.save(user);
                    } catch (AnimeListInfoAlreadyExist e) {
                        sender.sendMessage("Myanimelist account associated with you already exists. Remove the previous one to add new", Long.parseLong(chatId));
                    }

                    sender.sendMessage("Myanimelist account successfully authorized", Long.parseLong(chatId));
                },
                () -> sender.sendMessage("A problem has occurred, try to authorize again", Long.parseLong(chatId)));
    }
}
