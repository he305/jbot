package com.github.he305.jbot.telegram.infra;

import com.github.he305.jbot.telegram.domain.abstractions.ChatUserRepository;
import com.github.he305.jbot.telegram.domain.model.ChatUser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ChatUserRepositoryInMemory implements ChatUserRepository {
    List<ChatUser> chatUserList = new ArrayList<>();

    @Override
    public Optional<ChatUser> getChatUserByChatId(long chatId) {
        return chatUserList.stream().filter(chatUser -> chatUser.getChatId() == chatId).findFirst();
    }

    @Override
    public void save(ChatUser chatUser) {
        int index = -1;
        for (int i = 0; i < chatUserList.size(); i++) {
            if (chatUserList.get(i).getChatId() == chatUser.getChatId()) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            chatUserList.set(index, chatUser);
        } else {
            chatUserList.add(chatUser);
        }
    }
}
