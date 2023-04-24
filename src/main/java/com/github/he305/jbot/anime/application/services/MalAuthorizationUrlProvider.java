package com.github.he305.jbot.anime.application.services;

import com.github.he305.jbot.user.domain.model.User;

public interface MalAuthorizationUrlProvider {
    String getAuthUrl(User user);
}
