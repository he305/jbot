package com.github.he305.jbot.anime.application.services;

import com.github.he305.jbot.anime.domain.model.AnimeSeries;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;

public interface AnimeDataProvider {
    AnimeListType getType();

    AnimeSeries getAnimeData(String title);
}
