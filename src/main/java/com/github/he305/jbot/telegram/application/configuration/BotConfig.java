package com.github.he305.jbot.telegram.application.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class BotConfig {
    @Value("${telegram-name}")
    private String telegramName;
    @Value("${telegram-id}")
    private String telegramId;
}