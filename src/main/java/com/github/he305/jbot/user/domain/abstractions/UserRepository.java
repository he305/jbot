package com.github.he305.jbot.user.domain.abstractions;

import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.domain.model.values.ChatInfo;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAll();

    Optional<User> getByChatId(ChatInfo chatInfo);

    void save(User user);
}
