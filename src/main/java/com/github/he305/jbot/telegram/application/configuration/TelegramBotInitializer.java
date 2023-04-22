package com.github.he305.jbot.telegram.application.configuration;

import org.springframework.beans.factory.InitializingBean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.util.List;

public class TelegramBotInitializer implements InitializingBean {

    private final TelegramBotsApi telegramBotsApi;
    private final List<LongPollingBot> longPollingBots;

    public TelegramBotInitializer(TelegramBotsApi telegramBotsApi,
                                  List<LongPollingBot> longPollingBots) {
        this.telegramBotsApi = telegramBotsApi;
        this.longPollingBots = longPollingBots;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (LongPollingBot bot : longPollingBots) {
            telegramBotsApi.registerBot(bot);
        }
    }
}
