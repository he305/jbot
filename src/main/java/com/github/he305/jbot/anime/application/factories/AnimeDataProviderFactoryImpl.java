package com.github.he305.jbot.anime.application.factories;

import com.github.he305.jbot.anime.application.services.AnimeDataProvider;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;

@Component
public class AnimeDataProviderFactoryImpl implements AnimeDataProviderFactory {

    private final EnumMap<AnimeListType, AnimeDataProvider> typeToProvider;

    public AnimeDataProviderFactoryImpl(List<AnimeDataProvider> providers) {
        typeToProvider = new EnumMap<>(AnimeListType.class);

        providers.forEach(provider -> {
            AnimeListType type = provider.getType();
            typeToProvider.put(type, provider);
        });
    }

    @Override
    public AnimeDataProvider getAnimeDataProvider(AnimeListType type) {
        return typeToProvider.get(type);
    }
}
