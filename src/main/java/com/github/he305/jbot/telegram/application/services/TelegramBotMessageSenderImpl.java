package com.github.he305.jbot.telegram.application.services;

import com.github.he305.jbot.telegram.application.bots.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TelegramBotMessageSenderImpl implements TelegramBotMessageSender {
    private final TelegramBot bot;

    @Override
    public void sendMessage(String message, long chatId) {
        bot.silent().send(message, chatId);
    }
}
