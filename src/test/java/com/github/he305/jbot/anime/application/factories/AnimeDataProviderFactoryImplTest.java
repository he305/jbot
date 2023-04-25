package com.github.he305.jbot.anime.application.factories;

import com.github.he305.jbot.anime.application.services.AnimeDataProvider;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AnimeDataProviderFactoryImplTest {

    @Mock
    private AnimeDataProvider provider;

    private AnimeDataProviderFactoryImpl underTest;

    @BeforeEach
    void init() {
        Mockito.when(provider.getType()).thenReturn(AnimeListType.MYANIMELIST);
        underTest = new AnimeDataProviderFactoryImpl(List.of(provider));
    }

    @Test
    void getAnimeDataProvider() {
        AnimeListType type = AnimeListType.MYANIMELIST;
        AnimeDataProvider actual = underTest.getAnimeDataProvider(type);
        assertEquals(provider, actual);
    }
}