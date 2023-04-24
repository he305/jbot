package com.github.he305.jbot.anime.application.mappers;

import com.github.he305.jbot.anime.application.dtos.mal.entry.MalAnimeListEntryDto;
import com.github.he305.jbot.anime.domain.model.AnimeSeries;

public interface MalAnimeEntryMapper {
    AnimeSeries dtoToDomain(MalAnimeListEntryDto dto);
}
