package com.github.he305.jbot.telegram.application.services;

import com.github.he305.jbot.telegram.application.bots.TelegramBot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.abilitybots.api.sender.SilentSender;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class TelegramBotMessageSenderImplTest {

    @Mock
    private TelegramBot bot;

    @InjectMocks
    private TelegramBotMessageSenderImpl underTest;

    @Test
    void sendMessage() {
        String message = "123";
        long chatId = 123;
        SilentSender sender = Mockito.mock(SilentSender.class);
        Mockito.when(sender.send(message, chatId)).thenReturn(Optional.empty());
        Mockito.when(bot.silent()).thenReturn(sender);

        assertDoesNotThrow(() -> underTest.sendMessage(message, chatId));
    }
}