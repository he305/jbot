package com.github.he305.jbot.telegram.application.services;

public interface TelegramBotMessageSender {
    void sendMessage(String message, long chatId);
}
