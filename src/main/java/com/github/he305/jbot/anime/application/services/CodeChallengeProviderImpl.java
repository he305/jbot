package com.github.he305.jbot.anime.application.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CodeChallengeProviderImpl implements CodeChallengeProvider {

    @Value("${code-verifier:none}")
    private String code;

    @Override
    public String getCodeChallenge() {
        return code;
    }
}
