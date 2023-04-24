package com.github.he305.jbot.telegram.application.actions;

import com.github.he305.jbot.anime.application.services.MalAuthorizationUrlProvider;
import com.github.he305.jbot.telegram.application.services.GetUserByContextQuery;
import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.MessageContext;

@Component
@RequiredArgsConstructor
public class MalAuthAction implements SingleReplyAction {
    private final GetUserByContextQuery getUserByContextQuery;
    private final MalAuthorizationUrlProvider malAuthorizationUrlProvider;

    @Override
    public String getCommand() {
        return "mal_auth";
    }

    @Override
    public String getDescription() {
        return "provides link to authorize myanimelist account";
    }

    @Override
    public String getMessage(MessageContext context) {
        User user = getUserByContextQuery.execute(context);
        if (user.animeListInfoWithTypeExists(AnimeListType.MYANIMELIST)) {
            return "Myanimelist account associated with you already exists. Remove the previous one to add new";
        }
        String url = malAuthorizationUrlProvider.getAuthUrl(user);

        return String.format("Use this url to authorize myanimelist account: %s", url);
    }
}
