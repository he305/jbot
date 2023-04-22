package com.github.he305.jbot.telegram.application.bots;

import com.github.he305.jbot.telegram.application.configuration.BotConfig;
import com.github.he305.jbot.telegram.application.factories.AbilityExtensionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;

@Slf4j
@Component
public class TelegramBot extends AbilityBot {

    private final long creatorId;

    public TelegramBot(BotConfig botConfig, AbilityExtensionFactory abilityExtensionFactory) {
        super(botConfig.getTelegramId(), botConfig.getTelegramName());
        creatorId = botConfig.getTelegramCreatorId();
        abilityExtensionFactory.getAbilityExtensions(this).forEach(this::addExtension);
    }

    @Override
    public long creatorId() {
        return creatorId;
    }
}
