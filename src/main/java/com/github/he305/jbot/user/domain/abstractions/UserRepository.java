package com.github.he305.jbot.user.domain.abstractions;

import com.github.he305.jbot.user.domain.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();
    void save(User user);
}
