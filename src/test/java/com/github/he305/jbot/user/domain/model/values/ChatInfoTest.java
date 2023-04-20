package com.github.he305.jbot.user.domain.model.values;

import com.github.he305.jbot.common.exceptions.StringValidationException;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatInfoTest {

    @Test
    void ChatInfo_empty_should_fail() {
        String chatId = "";

        assertThrows(StringValidationException.class, () -> new ChatInfo(chatId));
    }

    @Test
    void ChatInfo_ok() {
        String chatId = "chatId";
        assertDoesNotThrow(() -> new ChatInfo(chatId));
    }

    @Test
    void ChatInfo_testEquals() {
        EqualsVerifier.simple().forClass(ChatInfo.class).verify();
    }
}