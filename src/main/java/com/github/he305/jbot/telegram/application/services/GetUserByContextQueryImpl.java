package com.github.he305.jbot.telegram.application.services;

import com.github.he305.jbot.user.domain.abstractions.UserService;
import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.domain.model.values.ChatInfo;
import com.github.he305.jbot.user.domain.model.values.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.abilitybots.api.objects.MessageContext;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetUserByContextQueryImpl implements GetUserByContextQuery {
    private final UserService userService;

    @Override
    public User execute(MessageContext context) {
        long chatId = context.chatId();
        Optional<User> optionalUser = userService.getByChatId(String.valueOf(chatId));
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }

        User user = User.create(new UserInfo(context.user().getFirstName()), new ChatInfo(String.valueOf(chatId)));
        userService.save(user);

        return user;
    }
}
