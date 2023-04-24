package com.github.he305.jbot.telegram.application.services;

public interface AnimeListAuthorizationService {
    void authorize(String authorizationCode, String chatId);
}
