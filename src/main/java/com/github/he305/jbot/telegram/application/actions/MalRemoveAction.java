package com.github.he305.jbot.telegram.application.actions;

import com.github.he305.jbot.telegram.application.services.GetUserByContextQuery;
import com.github.he305.jbot.user.domain.abstractions.UserService;
import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.MessageContext;

@Component
@RequiredArgsConstructor
public class MalRemoveAction implements SingleReplyAction {
    private final GetUserByContextQuery getUserByContextQuery;
    private final UserService userService;

    @Override
    public String getCommand() {
        return "mal_remove";
    }

    @Override
    public String getDescription() {
        return "remove existing myanimelist account";
    }

    @Override
    public String getMessage(MessageContext context) {
        User user = getUserByContextQuery.execute(context);

        if (user.removeAnimeListInfoType(AnimeListType.MYANIMELIST)) {
            userService.save(user);
            return "Successfully deleted myanimelist account";
        }

        return "Myanimelist account hasn't been found";
    }
}
