package com.github.he305.jbot.telegram.application.configuration;

import com.github.he305.jbot.telegram.application.TelegramBot;
import com.github.he305.jbot.telegram.domain.abstractions.ChatUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@Slf4j
public class Initializer {
    @Autowired
    BotConfig botConfig;
    @Autowired
    ChatUserRepository repository;

    @EventListener({ContextRefreshedEvent.class})
    public void init() {
        try {
            TelegramBot bot = new TelegramBot(botConfig.getTelegramId(), botConfig.getTelegramName(), repository);

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
