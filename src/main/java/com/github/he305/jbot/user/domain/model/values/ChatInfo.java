package com.github.he305.jbot.user.domain.model.values;

import com.github.he305.jbot.common.validators.StringValidator;
import lombok.NonNull;

public record ChatInfo(
        @NonNull String chatId
) {
    public ChatInfo(String chatId) {
        this.chatId = StringValidator.validate(chatId);
    }
}
