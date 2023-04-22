package com.github.he305.jbot.telegram.application.actions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.MessageContext;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HelpAction implements SingleReplyAction {

    private final List<BotAction> allActions;

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "display all commands";
    }

    @Override
    public String getMessage(MessageContext context) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("/%s - %s%n", getCommand(), getDescription()));
        allActions.forEach(botAction -> sb.append(String.format("/%s - %s%n", botAction.getCommand(), botAction.getDescription())));

        return sb.toString();
    }
}
