package com.github.he305.jbot.anime.application.services;

import com.github.he305.jbot.anime.domain.model.AnimeSeries;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;

import java.util.Optional;

public interface AnimeDataProvider {
    AnimeListType getType();

    Optional<AnimeSeries> getAnimeData(String title);
}
