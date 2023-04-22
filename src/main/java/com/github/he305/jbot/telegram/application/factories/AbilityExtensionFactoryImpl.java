package com.github.he305.jbot.telegram.application.factories;

import com.github.he305.jbot.telegram.application.abilities.SingleReplyAbility;
import com.github.he305.jbot.telegram.application.actions.SingleReplyAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.util.AbilityExtension;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AbilityExtensionFactoryImpl implements AbilityExtensionFactory {
    private final List<SingleReplyAction> actions;

    @Override
    public List<AbilityExtension> getAbilityExtensions(AbilityBot bot) {
        return actions.stream().map(action -> new SingleReplyAbility(action, bot)).collect(Collectors.toUnmodifiableList());
    }
}
