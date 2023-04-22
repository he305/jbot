package com.github.he305.jbot.telegram.application.actions;

import org.telegram.abilitybots.api.objects.MessageContext;

public interface SingleReplyAction extends BotAction {

    String getMessage(MessageContext context);
}
