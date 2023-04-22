package com.github.he305.jbot.telegram.application.actions;

import com.github.he305.jbot.telegram.application.services.GetUserByContextQuery;
import com.github.he305.jbot.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.MessageContext;

@Component
@RequiredArgsConstructor
public class StartAction implements SingleReplyAction {
    private final GetUserByContextQuery getUserByContextQuery;

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
        User user = getUserByContextQuery.execute(context);
        return String.format("Hello, %s! Feel free to use /help command", user.getUserInfo().getName());
    }
}
