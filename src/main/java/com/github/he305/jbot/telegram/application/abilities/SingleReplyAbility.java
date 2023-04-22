package com.github.he305.jbot.telegram.application.abilities;

import com.github.he305.jbot.telegram.application.actions.SingleReplyAction;
import lombok.RequiredArgsConstructor;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.abilitybots.api.util.AbilityExtension;

@RequiredArgsConstructor
public class SingleReplyAbility implements AbilityExtension {
    private final SingleReplyAction action;
    private final AbilityBot bot;

    public Ability ability() {
        return Ability.builder()
                .name(action.getCommand())
                .privacy(Privacy.PUBLIC)
                .locality(Locality.ALL)
                .action(ctx -> bot.silent().send(action.getMessage(ctx), ctx.chatId()))
                .build();
    }
}
