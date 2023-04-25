package com.github.he305.jbot.anime.application.services;

import com.github.he305.jbot.anime.application.dtos.mal.entry.MalAnimeListEntryDto;
import com.github.he305.jbot.anime.application.dtos.mal.entry.RootDto;
import com.github.he305.jbot.anime.application.mappers.MalAnimeEntryMapper;
import com.github.he305.jbot.anime.configuration.MalApiConfig;
import com.github.he305.jbot.anime.domain.model.AnimeSeries;
import com.github.he305.jbot.anime.exceptions.NetworkException;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MalAnimeDataProvider implements AnimeDataProvider {
    private static final String[] REQUEST_FIELDS = {
            "alternative_titles", "broadcast", "status", "start_date", "end_date", "my_list_status", "num_episodes",
    };
    private static final String BASE_PATH = "https://api.myanimelist.net/v2/anime";
    private final RestTemplate restTemplate;
    private final MalApiConfig malApiConfig;
    private final MalAnimeEntryMapper mapper;

    @Override
    public AnimeListType getType() {
        return AnimeListType.MYANIMELIST;
    }

    @Override
    public AnimeSeries getAnimeData(String title) {
        String clientId = malApiConfig.getClientId();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-MAL-CLIENT-ID", clientId);

        HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);

        String uri = UriComponentsBuilder
                .fromUri(URI.create(BASE_PATH))
                .queryParam("q", title)
                .queryParam("limit", 1)
                .queryParam("fields", joinRequestFields())
                .toUriString();

        ResponseEntity<RootDto> dto;
        try {
            dto = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, RootDto.class);
        } catch (ResourceAccessException | HttpServerErrorException | HttpClientErrorException ex) {
            throw new NetworkException(ex.getMessage());
        }

        MalAnimeListEntryDto animeListEntryDto = Objects.requireNonNull(dto.getBody()).getData()[0].getNode();

        return mapper.dtoToDomain(animeListEntryDto);
    }

    private String joinRequestFields() {
        return String.join(",", REQUEST_FIELDS);
    }
}
