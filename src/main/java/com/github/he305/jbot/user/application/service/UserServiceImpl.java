package com.github.he305.jbot.user.application.service;

import com.github.he305.jbot.user.domain.abstractions.UserRepository;
import com.github.he305.jbot.user.domain.abstractions.UserService;
import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.domain.model.values.ChatInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getByChatId(String chatId) {
        return userRepository.getByChatId(new ChatInfo(chatId));
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }
}
