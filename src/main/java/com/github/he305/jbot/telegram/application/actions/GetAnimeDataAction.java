package com.github.he305.jbot.telegram.application.actions;

import com.github.he305.jbot.anime.application.factories.AnimeDataProviderFactory;
import com.github.he305.jbot.anime.application.services.AnimeDataProvider;
import com.github.he305.jbot.anime.domain.model.AnimeSeries;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.MessageContext;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetAnimeDataAction implements SingleReplyAction {

    private final AnimeDataProviderFactory animeDataProviderFactory;

    @Override
    public String getCommand() {
        return "get_anime_data";
    }

    @Override
    public String getDescription() {
        return "retrieves anime data from anime list (/get_anime_data anime_name)";
    }

    @Override
    public String getMessage(MessageContext context) {
        String[] argArray = context.arguments();
        if (argArray.length == 0) {
            return "Provide anime title (/get_anime_data anime_name)";
        }

        String animeTitle = String.join(" ", argArray);

        AnimeDataProvider provider = animeDataProviderFactory.getAnimeDataProvider(AnimeListType.MYANIMELIST);
        Optional<AnimeSeries> series = provider.getAnimeData(animeTitle);
        if (series.isEmpty()) {
            return "Couldn't find any anime with provided title";
        }
        return series.get().toString();
    }
}
