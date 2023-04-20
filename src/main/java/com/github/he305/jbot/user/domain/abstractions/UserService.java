package com.github.he305.jbot.user.domain.abstractions;

import com.github.he305.jbot.user.domain.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> getAll();
}
