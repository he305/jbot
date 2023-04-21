package com.github.he305.jbot.telegram.application;

import com.github.he305.jbot.telegram.application.configuration.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class TelegramBot extends AbilityBot {

    public TelegramBot(BotConfig botConfig) {
        super(botConfig.getTelegramId(), botConfig.getTelegramName());
    }

    @Override
    public long creatorId() {
        return 0;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String memberName = update.getMessage().getFrom().getFirstName();

            echo(chatId, memberName, messageText);
        }
    }



    private void sendMessage(SendMessage message) {
        try {
            execute(message);
            log.info(String.format("%s was sent", message));
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void echo(long chatId, String userName, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Hello, " + userName + "! " + text);

        sendMessage(message);
    }
}
