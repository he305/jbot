package com.github.he305.jbot.anime.application.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodeChallengeProviderImplTest {

    private final CodeChallengeProviderImpl underTest = new CodeChallengeProviderImpl();

    @Test
    void getCodeChallenge() {
        String actual = underTest.getCodeChallenge();
        assertEquals(43, actual.length());
    }
}