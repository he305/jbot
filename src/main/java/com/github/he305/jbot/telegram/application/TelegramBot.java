package com.github.he305.jbot.telegram.application;

import com.github.he305.jbot.telegram.domain.abstractions.ChatUserRepository;
import com.github.he305.jbot.telegram.domain.model.ChatUser;
import com.github.he305.jbot.telegram.domain.model.enums.ChatUserState;
import lombok.extern.slf4j.Slf4j;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class TelegramBot extends AbilityBot {
    private final ChatUserRepository chatUserRepository;

    public TelegramBot(String botToken, String botUsername, ChatUserRepository chatUserRepository) {
        super(botToken, botUsername);
        this.chatUserRepository = chatUserRepository;
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

            ChatUser chatUser = chatUserRepository.getChatUserByChatId(chatId).orElseGet(() ->
            {
                ChatUser chatUserTemp = new ChatUser(memberName, chatId, ChatUserState.FREE);
                chatUserRepository.save(chatUserTemp);
                return chatUserTemp;
            });

            if (messageText.startsWith("/")) {
                handleCommands(chatUser, messageText);
                return;
            }
            handleRawMessage(chatUser, messageText);
        }
    }

    private void handleRawMessage(ChatUser chatUser, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chatUser.getChatId());

        if (chatUser.getChatUserState() == ChatUserState.FREE) {
            sendHelpMessage(chatUser);
        }


    }

    private void handleCommands(ChatUser chatUser, String messageText) {
        if (messageText.equals("/register")) {
            startBot(chatUser.getChatId(), chatUser.getMemberName());
        } else {
            sendHelpMessage(chatUser);
        }
    }

    private void sendMessage(SendMessage message) {
        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendHelpMessage(ChatUser chatUser) {
        SendMessage message = new SendMessage();
        message.setChatId(chatUser.getChatId());
        message.setText("To get list of commands use /help");

        sendMessage(message);
    }

    private void startBot(long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Hello, " + userName + "! I'm a Telegram bot.");

        sendMessage(message);
    }
}
