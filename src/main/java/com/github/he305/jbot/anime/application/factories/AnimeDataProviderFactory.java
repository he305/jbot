package com.github.he305.jbot.anime.application.factories;

import com.github.he305.jbot.anime.application.services.AnimeDataProvider;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;

public interface AnimeDataProviderFactory {
    AnimeDataProvider getAnimeDataProvider(AnimeListType type);
}
