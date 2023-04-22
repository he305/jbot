package com.github.he305.jbot.telegram.application.factories;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.util.AbilityExtension;

import java.util.List;

public interface AbilityExtensionFactory {
    List<AbilityExtension> getAbilityExtensions(AbilityBot bot);
}
