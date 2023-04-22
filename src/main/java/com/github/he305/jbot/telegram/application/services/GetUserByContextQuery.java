package com.github.he305.jbot.telegram.application.services;

import com.github.he305.jbot.user.domain.model.User;
import org.telegram.abilitybots.api.objects.MessageContext;

public interface GetUserByContextQuery {
    User execute(MessageContext context);
}
