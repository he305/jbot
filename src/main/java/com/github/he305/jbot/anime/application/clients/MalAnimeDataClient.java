package com.github.he305.jbot.anime.application.clients;

import com.github.he305.jbot.anime.application.dtos.mal.entry.RootDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface MalAnimeDataClient {
    @GetExchange(url = "/anime")
    RootDto getAnimeData(@RequestParam("q") String title, @RequestParam("limit") int limit, @RequestParam("fields") String fields);
}
