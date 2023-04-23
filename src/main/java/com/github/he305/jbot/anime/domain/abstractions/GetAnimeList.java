package com.github.he305.jbot.anime.domain.abstractions;

import com.github.he305.jbot.anime.domain.model.AnimeSeries;

import java.util.List;

public interface GetAnimeList {
    List<AnimeSeries> execute();
}
