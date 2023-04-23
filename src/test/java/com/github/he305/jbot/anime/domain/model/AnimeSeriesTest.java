package com.github.he305.jbot.anime.domain.model;

import com.github.he305.jbot.anime.domain.model.enums.AiringStatus;
import com.github.he305.jbot.anime.domain.model.values.AiringDate;
import com.github.he305.jbot.anime.domain.model.values.TitleInfo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimeSeriesTest {

    @Test
    void testEquals() {
        int listId = 123;

        AnimeSeries first = new AnimeSeries(listId,
                new TitleInfo("1", Set.of()),
                new AiringDate(LocalDate.EPOCH, LocalDate.EPOCH),
                AiringStatus.NOT_STARTED,
                "");

        AnimeSeries second = new AnimeSeries(listId,
                new TitleInfo("2", Set.of("3")),
                new AiringDate(LocalDate.EPOCH, LocalDate.EPOCH),
                AiringStatus.AIRING,
                "test");

        assertEquals(first, second);
    }
}