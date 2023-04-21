package com.github.he305.jbot.telegram.domain.abstractions;

import com.github.he305.jbot.telegram.domain.model.ChatUser;

import java.util.Optional;

public interface ChatUserRepository {
    Optional<ChatUser> getChatUserByChatId(long chatId);

    void save(ChatUser chatUser);
}
