package com.github.he305.jbot.anime.application.services;

import com.github.he305.jbot.anime.application.clients.MalAnimeDataClient;
import com.github.he305.jbot.anime.application.dtos.mal.entry.DataDto;
import com.github.he305.jbot.anime.application.dtos.mal.entry.MalAnimeListEntryDto;
import com.github.he305.jbot.anime.application.dtos.mal.entry.RootDto;
import com.github.he305.jbot.anime.application.mappers.MalAnimeEntryMapper;
import com.github.he305.jbot.anime.domain.model.AnimeSeries;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class MalAnimeDataProviderTest {
    @Mock
    private MalAnimeDataClient client;
    @Mock
    private MalAnimeEntryMapper mapper;

    @InjectMocks
    private MalAnimeDataProvider underTest;

    @Test
    void getType() {
        AnimeListType expected = AnimeListType.MYANIMELIST;
        AnimeListType actual = underTest.getType();
        assertEquals(expected, actual);
    }

    @Test
    void getAnimeData_empty() {
        RootDto dto = Mockito.mock(RootDto.class);
        Mockito.when(dto.getData()).thenReturn(new DataDto[]{});

        Mockito.when(client.getAnimeData(Mockito.anyString(), Mockito.anyInt(), Mockito.anyString())).thenReturn(dto);

        Optional<AnimeSeries> expected = Optional.empty();
        Optional<AnimeSeries> actual = underTest.getAnimeData("t");
        assertEquals(expected, actual);
    }

    @Test
    void getAnimeData_exception() {
        Mockito.when(client.getAnimeData(Mockito.anyString(), Mockito.anyInt(), Mockito.anyString())).thenThrow(WebClientResponseException.class);

        Optional<AnimeSeries> expected = Optional.empty();
        Optional<AnimeSeries> actual = underTest.getAnimeData("t");
        assertEquals(expected, actual);
    }

    @Test
    void getAnimeData_ok() {
        RootDto dto = Mockito.mock(RootDto.class);
        DataDto dataDto = Mockito.mock(DataDto.class);
        MalAnimeListEntryDto malAnimeListEntryDto = Mockito.mock(MalAnimeListEntryDto.class);
        Mockito.when(dto.getData()).thenReturn(new DataDto[]{dataDto});
        Mockito.when(dataDto.getNode()).thenReturn(malAnimeListEntryDto);

        Mockito.when(client.getAnimeData(Mockito.anyString(), Mockito.anyInt(), Mockito.anyString())).thenReturn(dto);

        AnimeSeries expected = Mockito.mock(AnimeSeries.class);
        Mockito.when(mapper.dtoToDomain(malAnimeListEntryDto)).thenReturn(expected);

        Optional<AnimeSeries> actual = underTest.getAnimeData("t");
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }
}