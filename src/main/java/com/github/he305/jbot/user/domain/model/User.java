package com.github.he305.jbot.user.domain.model;

import com.github.he305.jbot.user.domain.model.values.ChatInfo;
import com.github.he305.jbot.user.domain.model.values.UserInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "userId")
@ToString
public class User {
    private final @NonNull UUID userId;
    private final @NonNull UserInfo userInfo;
    private final @NonNull ChatInfo chatInfo;

    public User(UUID userId, UserInfo userInfo, ChatInfo chatInfo) {
        this.userId = userId;
        this.userInfo = userInfo;
        this.chatInfo = chatInfo;
    }

    public static User create(UserInfo userInfo, ChatInfo chatInfo) {
        UUID id = UUID.randomUUID();

        return new User(id, userInfo, chatInfo);
    }

    public static User getExistent(UUID id, UserInfo userInfo, ChatInfo chatInfo) {
        return new User(id, userInfo, chatInfo);
    }
}
