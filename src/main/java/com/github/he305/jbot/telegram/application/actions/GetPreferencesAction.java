package com.github.he305.jbot.telegram.application.actions;

import com.github.he305.jbot.telegram.application.services.GetUserByContextQuery;
import com.github.he305.jbot.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.MessageContext;

@Component
@RequiredArgsConstructor
public class GetPreferencesAction implements SingleReplyAction {
    private final GetUserByContextQuery getUserByContextQuery;

    @Override
    public String getCommand() {
        return "get_preferences";
    }

    @Override
    public String getDescription() {
        return "display current anime source preferences";
    }

    @Override
    public String getMessage(MessageContext context) {
        User user = getUserByContextQuery.execute(context);

        String audioSourceString = String.valueOf(user.getAudioSource()).toLowerCase();
        String subsSourceString = String.valueOf(user.getSubsSource()).toLowerCase();

        return String.format("Audio source: %s, subs source: %s", audioSourceString, subsSourceString);
    }
}
