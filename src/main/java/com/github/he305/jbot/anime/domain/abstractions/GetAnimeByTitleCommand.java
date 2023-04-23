package com.github.he305.jbot.anime.domain.abstractions;

import com.github.he305.jbot.anime.domain.model.AnimeSeries;

public interface GetAnimeByTitleCommand {
    AnimeSeries execute(String title);
}
