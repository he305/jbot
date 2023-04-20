package com.github.he305.jbot.user.domain.model.values;

import com.github.he305.jbot.common.validators.StringValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class ChatInfo {
    private String chatId;

    public ChatInfo(String chatId) {
        this.chatId = StringValidator.validate(chatId);
    }
}
