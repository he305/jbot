package com.github.he305.jbot.anime.application.services;

import com.github.he305.jbot.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodeChallengeProviderImplTest extends IntegrationTestBase {

    @Autowired
    private CodeChallengeProviderImpl underTest;

    @Test
    void getCodeChallenge() {
        String actual = underTest.getCodeChallenge();
        assertEquals(43, actual.length());
    }
}