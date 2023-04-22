package com.github.he305.jbot.user.domain.abstractions;

import com.github.he305.jbot.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);

    Optional<User> getByChatId(String chatId);

    List<User> getAll();
}
