package com.github.he305.jbot.telegram.application.actions;

import com.github.he305.jbot.anime.application.factories.AnimeDataProviderFactory;
import com.github.he305.jbot.anime.application.services.AnimeDataProvider;
import com.github.he305.jbot.anime.domain.model.AnimeSeries;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.abilitybots.api.objects.MessageContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GetAnimeDataActionTest {

    @Mock
    private MessageContext context;
    @Mock
    private AnimeDataProviderFactory factory;

    @InjectMocks
    private GetAnimeDataAction underTest;

    @Test
    void getCommand() {
        String expected = "get_anime_data";
        assertEquals(expected, underTest.getCommand());
    }

    @Test
    void getDescription() {
        String expected = "retrieves anime data from anime list (/get_anime_data anime_name)";
        assertEquals(expected, underTest.getDescription());
    }

    @Test
    void getMessage_invalidInput() {
        Mockito.when(context.arguments()).thenReturn(new String[]{});
        String expected = "Provide anime title (/get_anime_data anime_name)";
        String actual = underTest.getMessage(context);
        assertEquals(expected, actual);
    }

    @Test
    void getMessage_noResult() {
        Mockito.when(context.arguments()).thenReturn(new String[]{"data"});
        AnimeDataProvider dataProvider = Mockito.mock(AnimeDataProvider.class);
        Mockito.when(factory.getAnimeDataProvider(AnimeListType.MYANIMELIST)).thenReturn(dataProvider);
        Mockito.when(dataProvider.getAnimeData("data")).thenReturn(Optional.empty());

        String expected = "Couldn't find any anime with provided title";
        String actual = underTest.getMessage(context);
        assertEquals(expected, actual);
    }

    @Test
    void getMessage_ok() {
        String expected = "smth";

        Mockito.when(context.arguments()).thenReturn(new String[]{"data"});
        AnimeSeries series = Mockito.mock(AnimeSeries.class);
        AnimeDataProvider dataProvider = Mockito.mock(AnimeDataProvider.class);
        Mockito.when(factory.getAnimeDataProvider(AnimeListType.MYANIMELIST)).thenReturn(dataProvider);
        Mockito.when(dataProvider.getAnimeData("data")).thenReturn(Optional.of(series));
        Mockito.when(series.toString()).thenReturn(expected);

        String actual = underTest.getMessage(context);
        assertEquals(expected, actual);
    }
}