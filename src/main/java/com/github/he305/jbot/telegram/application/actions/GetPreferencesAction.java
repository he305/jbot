package com.github.he305.jbot.telegram.application.actions;

import com.github.he305.jbot.telegram.application.exceptions.UnexpectedStateException;
import com.github.he305.jbot.user.domain.abstractions.UserService;
import com.github.he305.jbot.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.MessageContext;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetPreferencesAction implements SingleReplyAction {
    private final UserService userService;

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
        long chatId = context.chatId();
        Optional<User> optionalUser = userService.getByChatId(String.valueOf(chatId));
        if (optionalUser.isEmpty()) {
            throw new UnexpectedStateException(
                    String.format("User with name %s and chat id %d tried to use command while being unregistered",
                            context.user().getFirstName(),
                            chatId));
        }

        User user = optionalUser.get();

        String audioSourceString = String.valueOf(user.getAudioSource()).toLowerCase();
        String subsSourceString = String.valueOf(user.getSubsSource()).toLowerCase();

        return String.format("Audio source: %s, subs source: %s", audioSourceString, subsSourceString);
    }
}
