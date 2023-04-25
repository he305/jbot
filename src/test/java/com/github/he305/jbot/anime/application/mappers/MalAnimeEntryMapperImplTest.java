package com.github.he305.jbot.anime.application.mappers;

import com.github.he305.jbot.anime.application.dtos.mal.entry.AlternativeTitlesDto;
import com.github.he305.jbot.anime.application.dtos.mal.entry.BroadcastDto;
import com.github.he305.jbot.anime.application.dtos.mal.entry.MalAnimeListEntryDto;
import com.github.he305.jbot.anime.application.dtos.mal.entry.PictureDto;
import com.github.he305.jbot.anime.domain.model.AnimeSeries;
import com.github.he305.jbot.anime.domain.model.enums.AiringStatus;
import com.github.he305.jbot.anime.domain.model.values.AiringDate;
import com.github.he305.jbot.anime.domain.model.values.TitleInfo;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MalAnimeEntryMapperImplTest {

    private final MalAnimeEntryMapperImpl underTest = new MalAnimeEntryMapperImpl();

    @Test
    void dtoToDomain_ok_airing() {
        int id = 123;
        String title = "title";
        AiringStatus airingStatus = AiringStatus.AIRING;
        String image = "image";

        AnimeSeries expected = new AnimeSeries(
                id,
                new TitleInfo(title, Set.of()),
                new AiringDate(null, null),
                airingStatus,
                image
        );

        MalAnimeListEntryDto dto = new MalAnimeListEntryDto(
                id,
                title,
                new PictureDto("", image),
                new AlternativeTitlesDto(new String[]{}, "", ""),
                new BroadcastDto("w", "st"),
                "currently_airing",
                null,
                null,
                null,
                13
        );

        AnimeSeries actual = underTest.dtoToDomain(dto);
        assertEquals(expected, actual);
    }

    @Test
    void dtoToDomain_ok_complete_bad_date() {
        int id = 123;
        String title = "title";
        AiringStatus airingStatus = AiringStatus.COMPLETE;
        String image = "image";

        AnimeSeries expected = new AnimeSeries(
                id,
                new TitleInfo(title, Set.of()),
                new AiringDate(null, null),
                airingStatus,
                image
        );

        MalAnimeListEntryDto dto = new MalAnimeListEntryDto(
                id,
                title,
                new PictureDto("", image),
                new AlternativeTitlesDto(new String[]{}, "", ""),
                new BroadcastDto("w", "st"),
                "finished_airing",
                "2020",
                null,
                null,
                13
        );

        AnimeSeries actual = underTest.dtoToDomain(dto);
        assertEquals(expected, actual);
    }

    @Test
    void dtoToDomain_ok_not_started() {
        int id = 123;
        String title = "title";
        AiringStatus airingStatus = AiringStatus.NOT_STARTED;
        String image = "image";

        AnimeSeries expected = new AnimeSeries(
                id,
                new TitleInfo(title, Set.of()),
                new AiringDate(null, null),
                airingStatus,
                image
        );

        MalAnimeListEntryDto dto = new MalAnimeListEntryDto(
                id,
                title,
                new PictureDto("", image),
                new AlternativeTitlesDto(new String[]{}, "", ""),
                new BroadcastDto("w", "st"),
                "not_started",
                null,
                null,
                null,
                13
        );

        AnimeSeries actual = underTest.dtoToDomain(dto);
        assertEquals(expected, actual);
    }


}