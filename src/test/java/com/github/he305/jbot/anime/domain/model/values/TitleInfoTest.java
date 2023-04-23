package com.github.he305.jbot.anime.domain.model.values;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TitleInfoTest {

    @Test
    void getTitlesAsSet() {
        String mainTitle = "test";
        Set<String> synonyms = new HashSet<>(Set.of("test1", "test2", "test3"));

        Set<String> expected = Set.of("test", "test1", "test2", "test3");
        TitleInfo titleInfo = new TitleInfo(mainTitle, synonyms);
        // Check if deep copied
        synonyms.add("test4");

        Set<String> actual = titleInfo.getTitlesAsSet();
        assertEquals(expected, actual);
    }

    @Test
    void getTitlesAsString() {
        String mainTitle = "test";
        Set<String> synonyms = new HashSet<>(Set.of("a", "b", "c"));

        TitleInfo titleInfo = new TitleInfo(mainTitle, synonyms);

        String actual = titleInfo.getTitlesAsString();
        assertTrue(actual.contains(mainTitle));
        synonyms.forEach(s -> assertTrue(actual.contains(s)));
    }

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(TitleInfo.class).verify();
    }
}