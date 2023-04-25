package com.github.he305.jbot.anime.configuration;

import com.github.he305.jbot.anime.application.clients.MalAnimeDataClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class MalAnimeDataConfiguration {

    @Bean
    public MalAnimeDataClient malAnimeDataClient(MalApiConfig malApiConfig) {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.myanimelist.net/v2")
                .defaultHeader("X-MAL-CLIENT-ID", malApiConfig.getClientId())
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();

        return factory.createClient(MalAnimeDataClient.class);
    }
}
