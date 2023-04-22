package com.github.he305.jbot.telegram.application.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.util.List;


@Configuration
public class TelegramBotInitializer {

    private final TelegramBotsApi telegramBotsApi;
    private final List<LongPollingBot> longPollingBots;

    public TelegramBotInitializer(TelegramBotsApi telegramBotsApi,
                                  List<LongPollingBot> longPollingBots) {
        this.telegramBotsApi = telegramBotsApi;
        this.longPollingBots = longPollingBots;
    }

    @PostConstruct
    public void afterPropertiesSet() throws TelegramApiException {
        for (LongPollingBot bot : longPollingBots) {
            telegramBotsApi.registerBot(bot);
        }
    }
}
