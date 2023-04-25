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

    @Test
    void test_toString() {
        AnimeSeries first = new AnimeSeries(123,
                new TitleInfo("1", Set.of()),
                new AiringDate(LocalDate.EPOCH, LocalDate.EPOCH),
                AiringStatus.NOT_STARTED,
                "image");

        String expected = "1\nCurrent status: not started, start date: 1970-01-01, end date: 1970-01-01, url: image";

        String actual = first.toString();
        assertEquals(expected, actual);
    }

    @Test
    void test_toString_nullDates() {
        AnimeSeries first = new AnimeSeries(123,
                new TitleInfo("1", Set.of()),
                new AiringDate(null, null),
                AiringStatus.NOT_STARTED,
                "image");

        String expected = "1\nCurrent status: not started, url: image";

        String actual = first.toString();
        assertEquals(expected, actual);
    }
}