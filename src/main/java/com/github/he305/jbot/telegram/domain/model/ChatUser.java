package com.github.he305.jbot.telegram.domain.model;

import com.github.he305.jbot.telegram.domain.model.enums.ChatUserState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ChatUser {
    private String memberName;
    private long chatId;
    private ChatUserState chatUserState;
}
