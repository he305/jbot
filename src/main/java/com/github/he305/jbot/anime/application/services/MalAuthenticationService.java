package com.github.he305.jbot.anime.application.services;

import com.github.he305.jbot.anime.dtos.TokenDto;

public interface MalAuthenticationService {
    TokenDto getToken(String authorizationCode);

    TokenDto refreshToken(String refreshToken);
}
