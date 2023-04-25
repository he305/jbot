package com.github.he305.jbot.anime.application.services;

import com.github.he305.jbot.anime.application.clients.MalAnimeDataClient;
import com.github.he305.jbot.anime.application.dtos.mal.entry.MalAnimeListEntryDto;
import com.github.he305.jbot.anime.application.dtos.mal.entry.RootDto;
import com.github.he305.jbot.anime.application.mappers.MalAnimeEntryMapper;
import com.github.he305.jbot.anime.domain.model.AnimeSeries;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MalAnimeDataProvider implements AnimeDataProvider {
    private static final String[] REQUEST_FIELDS = {
            "alternative_titles", "broadcast", "status", "start_date", "end_date", "my_list_status", "num_episodes",
    };
    private final MalAnimeDataClient client;
    private final MalAnimeEntryMapper mapper;

    @Override
    public AnimeListType getType() {
        return AnimeListType.MYANIMELIST;
    }

    @Override
    public Optional<AnimeSeries> getAnimeData(String title) {
        try {
            RootDto dto = client.getAnimeData(title, 1, joinRequestFields());
            if (dto.getData().length == 0) {
                return Optional.empty();
            }
            MalAnimeListEntryDto animeListEntryDto = Objects.requireNonNull(dto).getData()[0].getNode();
            return Optional.ofNullable(mapper.dtoToDomain(animeListEntryDto));
        } catch (WebClientResponseException e) {
            log.error("couldn't retrieve data for " + title + ", error: " + e.getMessage());
            return Optional.empty();
        }
    }

    private String joinRequestFields() {
        return String.join(",", REQUEST_FIELDS);
    }
}
