package com.github.he305.jbot.telegram.application.actions;

import com.github.he305.jbot.user.domain.abstractions.UserService;
import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.domain.model.values.ChatInfo;
import com.github.he305.jbot.user.domain.model.values.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.MessageContext;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StartAction implements SingleReplyAction {
    private final UserService userService;

    @Override
    public String getCommand() {
        return "start";
    }

    @Override
    public String getDescription() {
        return "registration command";
    }

    @Override
    public String getMessage(MessageContext context) {
        long chatId = context.chatId();
        Optional<User> optionalUser = userService.getByChatId(String.valueOf(chatId));
        if (optionalUser.isPresent()) {
            return String.format("Welcome back, %s! Feel free to use /help command", context.user().getFirstName());
        }

        User user = User.create(new UserInfo(context.user().getFirstName()), new ChatInfo(String.valueOf(chatId)));
        userService.save(user);

        return String.format("Hello, %s! Feel free to use /help command", context.user().getFirstName());
    }
}
