package com.github.he305.jbot.anime.application.services;

import com.github.he305.jbot.anime.configuration.MalApiConfig;
import com.github.he305.jbot.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MalAuthorizationUrlProviderImpl implements MalAuthorizationUrlProvider {

    private static final String BASE_URL = "https://myanimelist.net/v1/oauth2/authorize";
    private static final String RESPONSE_TYPE = "code";
    private final CodeChallengeProvider codeChallengeProvider;
    private final MalApiConfig malApiConfig;

    @Override
    public String getAuthUrl(User user) {
        String codeChallenge = codeChallengeProvider.getCodeChallenge();
        String clientId = malApiConfig.getClientId();
        String state = user.getChatInfo().getChatId();

        return String.format("%s?response_type=%s&client_id=%s&code_challenge=%s&state=%s",
                BASE_URL,
                RESPONSE_TYPE,
                clientId,
                codeChallenge,
                state);
    }
}
